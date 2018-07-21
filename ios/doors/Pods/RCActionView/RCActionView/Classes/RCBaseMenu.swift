//
//  RCBaseMenu.swift
//  Pods
//
//  Created by Rodrigo on 10/06/2016.
//
//

import Foundation
import QuartzCore


class RCButton: UIButton {
    
    override var isHighlighted: Bool {
        willSet(newValue) {
            super.isSelected = newValue;
            highlight(highlighted: newValue)
        }
    }
    
    func highlight(highlighted: Bool) {
        if highlighted {
            self.backgroundColor = UIColor.lightGray
        }
        else {
            var delayInSeconds: Double = 0.2
            let popTime: DispatchTime = DispatchTime.now() + Double(Int64(delayInSeconds * Double(NSEC_PER_SEC))) / Double(NSEC_PER_SEC)
            DispatchQueue.main.asyncAfter(deadline: popTime, execute: {() -> Void in
                self.backgroundColor = UIColor(red: 13/255, green: 36/255, blue: 61/255, alpha: 1)//UIColor.clear
            })
        }
    }
}

class RCBaseMenu: UIView {
    
    var roundedCorner: Bool?
    var style: RCActionViewStyle
    
    override init(frame: CGRect) {
        self.style = .Light
        super.init(frame: frame)
        
        setRoundedCorner(roundedCorner: self.nicePerformance())
        
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    
    static func BaseMenuBackgroundColor(style:RCActionViewStyle?) -> UIColor{
        if (style == RCActionViewStyle.Light){
            return UIColor(white: 1.0, alpha: 1.0)
        } else{
            return UIColor(white: 0.2, alpha: 1.0)
        }
    }
    
    static func BaseMenuTextColor(style:RCActionViewStyle?) -> UIColor{
        if (style == RCActionViewStyle.Light){
            return UIColor.darkText
        } else{
            return UIColor.lightText
        }
    }
    
    static func BaseMenuActionTextColor() -> UIColor{
        return UIColor(red: 0.0, green: 122.0/255.0, blue: 1.0, alpha: 1.0)
    }
    
    
    
    func setRoundedCorner(roundedCorner: Bool) {
        if roundedCorner {
            var path: UIBezierPath = UIBezierPath(roundedRect: self.bounds, byRoundingCorners: [.topLeft, .topRight, .bottomLeft, .bottomRight], cornerRadii: CGSize(width: 8,height: 8))
            var maskLayer: CAShapeLayer = CAShapeLayer()
            maskLayer.frame = self.bounds
            maskLayer.path = path.cgPath
            self.layer.mask = maskLayer
        }
        else {
            self.layer.mask = nil
        }
        self.clipsToBounds = true
        self.roundedCorner = roundedCorner
        self.setNeedsDisplay()
    }
    
    func nicePerformance() -> Bool {
        var size : Int = 0
        sysctlbyname("hw.machine", nil, &size, nil, 0)
        var name = [CChar](repeating: 0, count: Int(size))
        sysctlbyname("hw.machine", &name, &size, nil, 0)
        var machine: NSString = String(describing: name) as NSString
        
        var b: Bool = true
        if machine.hasPrefix("iPhone") {
            b = CInt(machine.substring(with: NSRange(location: 6, length: 1)))! >= 4
        }
        else if machine.hasPrefix("iPod") {
            b = CInt(machine.substring(with: NSMakeRange(4, 1)))! >= 5
        }
        else if machine.hasPrefix("iPad") {
            b = CInt(machine.substring(with: NSMakeRange(4, 1)))! >= 2
        }
        
        return b
    }
    
    
    
}
