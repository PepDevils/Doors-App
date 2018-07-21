//
//  RCGridMenu.swift
//  Pods
//
//  Created by Rodrigo on 10/06/2016.
//
//

import Foundation

class RCGridItem: UIButton {
    
    init() {
        super.init(frame: CGRect.zero)
    }
    
    convenience init(title: String, image: UIImage, font: UIFont, style: RCActionViewStyle?) {
        self.init()
        
        self.clipsToBounds = false
        self.titleLabel!.font = font//UIFont.systemFont(ofSize: 13)
        self.titleLabel!.backgroundColor = UIColor.clear
        self.titleLabel!.textAlignment = .center
        self.setTitle(title, for: .normal)
        self.setTitleColor(RCBaseMenu.BaseMenuTextColor(style: style), for: .normal)
        self.setImage(image, for: .normal)
        
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        var width = self.bounds.size.width
        var height = self.bounds.size.height
        var imageRect = CGRect()
        if UIScreen.main.bounds.width > 500 {
            imageRect = CGRect(x: width * 0.35, y: 0/*width * 0.2*/, width: width * 0.3, height: width * 0.3)
        }
        else {
            imageRect = CGRect(x: width * 0.2, y: width * 0.2, width: width * 0.6, height: width * 0.6)
        }
        self.imageView!.frame = imageRect
        var labelHeight = height - (imageRect.origin.y + imageRect.size.height)
        var labelRect = CGRect(x: width * 0.05,y: imageRect.origin.y + imageRect.size.height,width: width * 0.9,height: labelHeight)
        self.titleLabel!.frame = labelRect
    }
}

class RCGridMenu: RCBaseMenu{
    
    let kMAX_CONTENT_SCROLLVIEW_HEIGHT:CGFloat = 400
    
    var titleLabel: UILabel
    var contentScrollView: UIScrollView
    var cancelButton: RCButton
    var itemTitles: [String]
    var itemImages: [UIImage]
    var items: [RCGridItem]
    
    var actionHandle: ((NSInteger) -> Void)?
    
    override init(frame: CGRect) {
        self.itemTitles = []
        self.itemImages = []
        self.items = []
        self.titleLabel = UILabel(frame: CGRect.zero)
        self.contentScrollView = UIScrollView(frame: CGRect.zero)
        self.cancelButton = RCButton(type: .custom)
        
        
        super.init(frame: frame)
        
        
    }
    
    convenience init(title: String, itemTitles: [String], images: [UIImage], font: UIFont, fontBold: UIFont, style: RCActionViewStyle) {
        self.init(frame: UIScreen.main.bounds)
        var count: Int = min(itemTitles.count, images.count)
        self.titleLabel.text = title
        self.itemTitles = itemTitles
        self.itemImages = images
        
        self.style = style
        
        if (style == .Light){
            self.cancelButton.setTitleColor(RCBaseMenu.BaseMenuActionTextColor(), for: .normal)
        } else {
            self.cancelButton.setTitleColor(RCBaseMenu.BaseMenuTextColor(style: self.style), for: .normal)
        }
        
        self.backgroundColor = RCBaseMenu.BaseMenuBackgroundColor(style: self.style)
        self.titleLabel.backgroundColor = UIColor.clear
        self.titleLabel.font = fontBold.withSize(17)//UIFont.boldSystemFont(ofSize: 17)
        self.titleLabel.textAlignment = .center
        self.titleLabel.textColor = RCBaseMenu.BaseMenuTextColor(style: self.style)
        self.addSubview(titleLabel)
        self.contentScrollView.contentSize = contentScrollView.bounds.size
        self.contentScrollView.showsHorizontalScrollIndicator = false
        self.contentScrollView.showsVerticalScrollIndicator = false
        self.contentScrollView.backgroundColor = UIColor.clear
        print(self.contentScrollView.center)
        print(self.contentScrollView.center)
        self.addSubview(contentScrollView)
        self.cancelButton.clipsToBounds = true
        self.cancelButton.titleLabel!.font = fontBold.withSize(17)//UIFont.systemFont(ofSize: 17)
        
        cancelButton.addTarget(self, action: "tapAction:", for: .touchUpInside)
        cancelButton.setTitle("Voltar", for: .normal)
        cancelButton.setTitleColor(UIColor.white, for: .normal)
        cancelButton.backgroundColor = UIColor(red: 13/255, green: 36/255, blue: 61/255, alpha: 1)
        //cancelButton.layer.cornerRadius = 4
        self.addSubview(cancelButton)
        //self.layer.cornerRadius = 8
        self.setupWithItemTitles(titles: itemTitles, images: itemImages, font: font)

        
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    
    func setupWithItemTitles(titles: [String], images: [UIImage], font: UIFont) {
        var items:[RCGridItem] = []
        for i in 0..<titles.count {
            var item: RCGridItem = RCGridItem(title: titles[i], image: images[i], font: font, style: style)
            item.tag = i
            item.addTarget(self, action: "tapAction:", for: .touchUpInside)
            items.append(item)
            contentScrollView.addSubview(item)
        }
        self.items = items
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        self.titleLabel.frame = CGRect(origin: CGPoint.zero, size: CGSize(width: self.bounds.size.width,height: 40))
        self.layoutContentScrollView()
        self.contentScrollView.frame = CGRect(origin: CGPoint(x: 0,y: self.titleLabel.frame.size.height), size: self.contentScrollView.bounds.size)
        print(self.contentScrollView.center)
        print(self.contentScrollView.center)
        self.cancelButton.frame = CGRect(x: /*self.bounds.size.width * 0.05*/0,y: self.titleLabel.bounds.size.height + self.contentScrollView.bounds.size.height,width: self.bounds.size.width/* * 0.9*/,height: 44)
        self.bounds = CGRect(origin: CGPoint.zero, size: CGSize(width: self.bounds.size.width, height: self.titleLabel.bounds.size.height + self.contentScrollView.bounds.size.height + self.cancelButton.bounds.size.height))
        self.layer.cornerRadius = 8
    }
    
    func layoutContentScrollView() {
        var margin: UIEdgeInsets = UIEdgeInsetsMake(0, 10, 15, 10)
        var itemSize: CGSize = CGSize(width: (self.bounds.size.width - margin.left - margin.right) / 4,height: 85)
        var itemCount: Int = self.items.count
        var rowCount: Int = ((itemCount - 1) / 4) + 1
        self.contentScrollView.contentSize = CGSize(width: self.bounds.size.width,height: CGFloat(rowCount) * itemSize.height + margin.top + margin.bottom)
        for i in 0..<itemCount {
            var item: RCGridItem = self.items[i]
            var row: Int = i / 4
            var column: Int = i % 3
            var p: CGPoint = CGPoint(x: margin.left + CGFloat(column) * itemSize.width,y: margin.top + CGFloat(row) * itemSize.height)
            
            item.frame = CGRect(origin: p, size: itemSize)
            if itemCount == 3
            {
                if i == 0
                {
                    item.center.x = self.center.x - itemSize.width/2 - 30
                }
                else if i == 1 {
                    item.center.x = self.center.x
                }
                else if i == 2
                {
                    item.center.x = self.center.x + itemSize.width/2 + 30
                }
            }
            else if itemCount == 2
            {
                if i == 0
                {
                    item.center.x = self.center.x - itemSize.width/2 - 10
                }
                else if i == 1 {
                    item.center.x = self.center.x + itemSize.width/2 + 10
                }
                
            }
            else if itemCount == 1
            {
                if i == 0
                {
                    item.center.x = self.center.x
                }
                
                
            }
            
            item.layoutIfNeeded()
        }
        if self.contentScrollView.contentSize.height > kMAX_CONTENT_SCROLLVIEW_HEIGHT {
            self.contentScrollView.bounds = CGRect(origin: CGPoint.zero,size: CGSize(width: self.bounds.size.width,height: kMAX_CONTENT_SCROLLVIEW_HEIGHT))
            //self.contentScrollView.center.x = self.center.x
        }
        else {
            self.contentScrollView.bounds = CGRect(origin: CGPoint.zero, size: self.contentScrollView.contentSize)
            //self.contentScrollView.center.x = self.center.x
        }
        
        
    }
    
    func triggerSelectedAction(actionHandle: @escaping (NSInteger) -> Void) {
        self.actionHandle = actionHandle
    }
    
    func tapAction(_ sender: AnyObject) {
        if let handle = actionHandle {
            if sender.isEqual(self.cancelButton) {
                var delayInSeconds: Double = 0.15
                let popTime: DispatchTime = DispatchTime.now() + Double(Int64(delayInSeconds * Double(NSEC_PER_SEC))) / Double(NSEC_PER_SEC)
                DispatchQueue.main.asyncAfter(deadline: popTime, execute: {() -> Void in
                    self.actionHandle!(0)
                })
            }
            else {
                var delayInSeconds: Double = 0.15
                let popTime: DispatchTime = DispatchTime.now() + Double(Int64(delayInSeconds * Double(NSEC_PER_SEC))) / Double(NSEC_PER_SEC)
                DispatchQueue.main.asyncAfter(deadline: popTime, execute: {() -> Void in
                    self.actionHandle!(sender.tag + 1)
                })
            }
        }
    }
    
    
}
