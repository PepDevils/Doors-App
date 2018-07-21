//
//  ViewControllerInstruction.swift
//  
//
//  Created by PEPDEVILS  on 03/01/2017.
//  Copyright © 2017 PEPDEVILS . All rights reserved.
//

import UIKit

class ViewControllerInstruction : UIViewController {
    
    @IBOutlet weak var CircularLabel1: UILabel!
    @IBOutlet weak var CircularLabel2: UILabel!
    @IBOutlet weak var CircularLabel3: UILabel!
    @IBOutlet weak var CircularLabel4: UILabel!
    @IBOutlet weak var CircularLabel5: UILabel!
    
    @IBOutlet weak var CircularLabel1HeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var CircularLabel1WidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var CircularLabel2HeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var CircularLabel2WidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var CircularLabel3HeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var CircularLabel3WidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var CircularLabel4HeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var CircularLabel4WidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var CircularLabel5HeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var CircularLabel5WidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var ImageAvisoHeight: NSLayoutConstraint!
    @IBOutlet weak var ImageAvisoWidth: NSLayoutConstraint!
    
    @IBOutlet weak var bt_iniciar: UIButton!
    
    @IBAction func go_back(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
    
    @IBOutlet weak var TopConstraint: NSLayoutConstraint!
    @IBOutlet weak var FirstLabelConstraint: NSLayoutConstraint!
    @IBOutlet weak var FirstLabelHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var SecondLabelHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var ThirthLabelHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var FourLabelHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var FiveLabelHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var SixLabelHeightConstraint: NSLayoutConstraint!
    
    @IBOutlet weak var SpaceCircularFirst: NSLayoutConstraint!
    @IBOutlet weak var SpaceLabelCircular2: NSLayoutConstraint!
    @IBOutlet weak var SpaceCircularSecond2: NSLayoutConstraint!
    @IBOutlet weak var SpaceLabelCircular3: NSLayoutConstraint!
    @IBOutlet weak var SpaceCircularLabel3: NSLayoutConstraint!
    @IBOutlet weak var SpaceLabelCircular4: NSLayoutConstraint!
    @IBOutlet weak var SpaceCircularLabel4: NSLayoutConstraint!
    @IBOutlet weak var SpaceLabelCircular5: NSLayoutConstraint!
    @IBOutlet weak var SpaceCircularLabel5: NSLayoutConstraint!
    @IBOutlet weak var SpaceLabelAviso6: NSLayoutConstraint!
    @IBOutlet weak var SpaceAvisoLabel6: NSLayoutConstraint!
    
    @IBOutlet weak var FirstLabelLeading: NSLayoutConstraint!
    @IBOutlet weak var FirstLabelTrailing: NSLayoutConstraint!
    @IBOutlet weak var FirstLabel: UILabel!
    @IBOutlet weak var SecondLabel: UILabel!
    @IBOutlet weak var ThirdLabel: UILabel!
    @IBOutlet weak var FourthLAbel: UILabel!
    @IBOutlet weak var FiveLabel: UILabel!
    @IBOutlet weak var scrollView: UIScrollView!
    @IBOutlet weak var ViewInstruction: UIView!
    @IBOutlet weak var SixLabel: UILabel!
    
    override func viewDidLoad() {
        
        CircularLabel1.backgroundColor = UIColor.init(red: 16/255, green: 36/255, blue: 61/255, alpha: 1)
        CircularLabel2.backgroundColor = UIColor.init(red: 16/255, green: 36/255, blue: 61/255, alpha: 1)
        CircularLabel3.backgroundColor = UIColor.init(red: 16/255, green: 36/255, blue: 61/255, alpha: 1)
        CircularLabel4.backgroundColor = UIColor.init(red: 16/255, green: 36/255, blue: 61/255, alpha: 1)
        CircularLabel5.backgroundColor = UIColor.init(red: 16/255, green: 36/255, blue: 61/255, alpha: 1)
        
        bt_iniciar.backgroundColor = UIColor.init(red: 115/255, green: 116/255, blue: 116/255, alpha: 1)
        
        if UIScreen.main.bounds.width > 500
        {
            TopConstraint.constant = 50
            CircularLabel1.font = CircularLabel1.font.withSize(21)
            CircularLabel2.font = CircularLabel2.font.withSize(21)
            CircularLabel3.font = CircularLabel3.font.withSize(21)
            CircularLabel4.font = CircularLabel4.font.withSize(21)
            CircularLabel5.font = CircularLabel5.font.withSize(21)
            
            FirstLabel.font = FirstLabel.font.withSize(21)
            SecondLabel.font = SecondLabel.font.withSize(21)
            ThirdLabel.font = ThirdLabel.font.withSize(21)
            FourthLAbel.font = FourthLAbel.font.withSize(21)
            FiveLabel.font = FiveLabel.font.withSize(21)
            SixLabel.font = SixLabel.font.withSize(18)
            FirstLabelConstraint.constant = 252
            
            FirstLabelHeightConstraint.constant = 42
            SecondLabelHeightConstraint.constant = 42
            ThirthLabelHeightConstraint.constant = 42
            FourLabelHeightConstraint.constant = 42
            FiveLabelHeightConstraint.constant = 42
            SixLabelHeightConstraint.constant = 75
                
            CircularLabel1HeightConstraint.constant = 50
            CircularLabel1WidthConstraint.constant = 50
            ImageAvisoHeight.constant = 25
            ImageAvisoWidth.constant = 25
            SpaceCircularFirst.constant = 16
            SpaceLabelCircular2.constant = 16
            SpaceCircularSecond2.constant = 16
            SpaceLabelCircular3.constant = 16
            SpaceCircularLabel3.constant = 16
            SpaceLabelCircular4.constant = 16
            SpaceCircularLabel4.constant = 16
            SpaceLabelCircular5.constant = 16
            SpaceCircularLabel5.constant = 16
            SpaceLabelAviso6.constant = 30
            SpaceAvisoLabel6.constant = 8
            
            CircularLabel1.frame.size.width = 50
            CircularLabel2HeightConstraint.constant = 50
            CircularLabel2WidthConstraint.constant = 50
            CircularLabel2.frame.size.width = 50
            CircularLabel3HeightConstraint.constant = 50
            CircularLabel3WidthConstraint.constant = 50
            CircularLabel3.frame.size.width = 50
            CircularLabel4HeightConstraint.constant = 50
            CircularLabel4WidthConstraint.constant = 50
            CircularLabel4.frame.size.width = 50
            CircularLabel5HeightConstraint.constant = 50
            CircularLabel5WidthConstraint.constant = 50
            CircularLabel5.frame.size.width = 50
            
            FirstLabelLeading.constant = -70
            FirstLabelTrailing.constant = 70
            
            CircularLabel1.layer.masksToBounds = true
            CircularLabel1.layer.cornerRadius = CircularLabel1.frame.size.width/2
            
            CircularLabel2.layer.masksToBounds = true
            CircularLabel2.layer.cornerRadius = CircularLabel2.frame.size.width/2
            
            CircularLabel3.layer.masksToBounds = true
            CircularLabel3.layer.cornerRadius = CircularLabel3.frame.size.width/2
            
            CircularLabel4.layer.masksToBounds = true
            CircularLabel4.layer.cornerRadius = CircularLabel4.frame.size.width/2
            
            CircularLabel5.layer.masksToBounds = true
            CircularLabel5.layer.cornerRadius = CircularLabel5.frame.size.width/2

        } else {
            CircularLabel1.layer.masksToBounds = true
            CircularLabel1.layer.cornerRadius = CircularLabel1.frame.size.width/2
            
            CircularLabel2.layer.masksToBounds = true
            CircularLabel2.layer.cornerRadius = CircularLabel2.frame.size.width/2
            
            CircularLabel3.layer.masksToBounds = true
            CircularLabel3.layer.cornerRadius = CircularLabel3.frame.size.width/2
            
            CircularLabel4.layer.masksToBounds = true
            CircularLabel4.layer.cornerRadius = CircularLabel4.frame.size.width/2
            
            CircularLabel5.layer.masksToBounds = true
            CircularLabel5.layer.cornerRadius = CircularLabel5.frame.size.width/2
            
            scrollView.contentSize.height = SixLabel.frame.origin.y + SixLabel.frame.size.height + bt_iniciar.frame.size.height + 100
            print(scrollView.contentSize.height)
        }
        
    }
    override var prefersStatusBarHidden: Bool {
        return true
    }
}
