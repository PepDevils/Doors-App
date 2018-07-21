//
//  ViewController.swift
//  
//
//  Created by PEPDEVILS  on 03/01/2017.
//  Copyright © 2017 PEPDEVILS . All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var InitialImage: UIImageView!
    @IBOutlet weak var logotipoHeightConstraint: NSLayoutConstraint!
    
    @IBOutlet weak var bt_instrucoesHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_instrucoesWidthConstrint: NSLayoutConstraint!
    @IBOutlet weak var bt_instrucoes: UIButton!
    @IBOutlet weak var bt_iniciar: UIButton!
    @IBOutlet weak var bt_iniciarHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_iniciarWidthConstraint: NSLayoutConstraint!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if UIScreen.main.bounds.width > 500 {
            logotipoHeightConstraint.constant = 100
            bt_instrucoesHeightConstraint.constant = 40
            bt_instrucoesWidthConstrint.constant = 400
            bt_instrucoes.titleLabel?.font = UIFont(name: (bt_instrucoes.titleLabel?.font.fontName)!, size: 17)
            //bt_instrucoes.titleLabel?.font.withSize(17)
            bt_iniciarHeightConstraint.constant = 40
            bt_iniciarWidthConstraint.constant = 400
            bt_iniciar.titleLabel?.font = UIFont(name: (bt_instrucoes.titleLabel?.font.fontName)!, size: 17)
            //bt_iniciar.titleLabel?.font.withSize(17)
        }
        
        bt_iniciar.layer.cornerRadius = 4
        bt_instrucoes.layer.cornerRadius = 4
        bt_instrucoes.backgroundColor = UIColor.init(red: 115/255, green: 116/255, blue: 116/255, alpha: 1)
        bt_iniciar.backgroundColor = UIColor.init(red: 115/255, green: 116/255, blue: 116/255, alpha: 1)
        InitialImage.backgroundColor = UIColor.init(red: 16/255, green: 36/255, blue: 61/255, alpha: 1)
        
        
        // Do any additional setup after loading the view, typically from a nib.
    }
    override func viewDidAppear(_ animated: Bool) {
        UIView.animate(withDuration: 5, delay: 0.5, options: [.repeat, .autoreverse], animations: {
            print(self.InitialImage.frame.origin.x)
            self.InitialImage.frame.origin.x = -1
        }, completion: nil)
    }
    override var prefersStatusBarHidden: Bool {
        return true
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

