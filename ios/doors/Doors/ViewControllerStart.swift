//
//  ViewControllerStart.swift
//  doors
//
//  Created by pepdevils  on 03/01/2017.
//  Copyright © 2017 pepdevils . All rights reserved.
//

import UIKit

class ViewControllerStart : UIViewController, UIImagePickerControllerDelegate, UINavigationControllerDelegate {
    
    @IBOutlet weak var TopLabel: UILabel!
    @IBOutlet weak var doorsLabel: UILabel!
    @IBOutlet weak var bt_photo: UIButton!
    @IBOutlet weak var bt_gallery: UIButton!
    
    //CONSTRAINT
    @IBOutlet weak var ViewHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_photoHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_photoWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_galleryHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_galleryWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var fotografiaHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var fotografiaWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var galeriaHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var galeiraWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_photoToTopConstraint: NSLayoutConstraint!
    @IBOutlet weak var topLabelHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var iconWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var iconHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_backHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_backWidthConstraint: NSLayoutConstraint!
    
    
    
    let customAlbum = CustomPhotoAlbum.sharedInstance
    let picker = UIImagePickerController()
    let imagePicked = UIImageView()
    
    @IBAction func go_back(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
    
    @IBAction func bt_photo(_ sender: Any) {
        
        if UIImagePickerController.availableCaptureModes(for: .rear) != nil {
            picker.allowsEditing = false
            picker.sourceType = .camera
            picker.cameraCaptureMode = .photo
            print(bt_photo.frame.size)
            present(picker, animated: true, completion: nil)
        }
        else
        {
            noCamera()
        }
    }
    @IBAction func bt_gallery(_ sender: Any) {
        picker.allowsEditing = false
        picker.sourceType = .photoLibrary
        
        print(bt_gallery.frame.size)
        present(picker, animated: true, completion: nil)
    }
    
    @IBAction func bt_doors(_ sender: Any) {
        if let url = URL(string: "http://www.doors.info"), UIApplication.shared.openURL(url) {//Workspace.shared().open(url) {
            print("default browser was successfully opened")
        }
    }
    
    override func viewDidLoad() {
        //print(bt_photo.frame.size)
        //print(bt_gallery.frame.size)
        
        bt_photo.setBackgroundImage(UIImage(named: "bt_highlighted"), for: .highlighted)
        bt_gallery.setBackgroundImage(UIImage(named: "bt_highlighted"), for: .highlighted)
        //bt_photo.setBackgroundImage(nil, for: .normal)
        if UIScreen.main.bounds.width > 500 {
            bt_photoToTopConstraint.constant = 30
            ViewHeightConstraint.constant = 350
            bt_photoHeightConstraint.constant = 147
            bt_photoWidthConstraint.constant = 150
            bt_galleryHeightConstraint.constant = 145
            bt_galleryWidthConstraint.constant = 193
            bt_photo.titleLabel?.font = UIFont(name: (bt_photo.titleLabel?.font.fontName)!, size: 23)
            bt_gallery.titleLabel?.font = UIFont(name: (bt_gallery.titleLabel?.font.fontName)!, size: 23)
            fotografiaHeightConstraint.constant = 120
            fotografiaWidthConstraint.constant = 120
            galeriaHeightConstraint.constant = 110
            galeiraWidthConstraint.constant = 110
            topLabelHeightConstraint.constant = 100
            iconWidthConstraint.constant = 90
            iconHeightConstraint.constant = 90
            bt_backHeightConstraint.constant = 40
            bt_backWidthConstraint.constant = 40
        }
        //print(bt_photo.frame.size)
        //print(bt_gallery.frame.size)
        
        UIApplication.shared.isStatusBarHidden = true
        picker.delegate = self
        
        TopLabel.backgroundColor = UIColor.init(red: 16/255, green: 36/255, blue: 61/255, alpha: 1)
        
        doorsLabel.backgroundColor = UIColor.init(red: 115/255, green: 116/255, blue: 116/255, alpha: 1)
        
        bt_photo.titleLabel?.textColor = UIColor.init(red: 115/255, green: 116/255, blue: 116/255, alpha: 1)
        
        bt_gallery.titleLabel?.textColor = UIColor.init(red: 115/255, green: 116/255, blue: 116/255, alpha: 1)
    }
    
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String : Any]) {
        let chosenImage = info[UIImagePickerControllerOriginalImage] as! UIImage
        imagePicked.contentMode = .scaleAspectFit
        imagePicked.image = chosenImage
        
        if (picker.sourceType == .camera)
        {
            customAlbum.saveImage(image: chosenImage)
        }
        
        picker.dismiss(animated: true, completion: nil)
        performSegue(withIdentifier: "segueMain", sender: nil)
        //imageDoor.image = UIImage(named: "porta")
        //dismiss(animated: true, completion: nil)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "segueMain" {
            let vc = segue.destination as! ViewControllerMain
            vc.newimage = imagePicked.image!
            //imagePickerBool = false
        }
    }
    func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
        dismiss(animated: true, completion: nil)
    }
    
    override var prefersStatusBarHidden: Bool {
        return true
    }
    
    func noCamera(){
        let alertVC = UIAlertController(title: "Câmara não disponível", message: "O seu dispositivo não possui uma câmara disponível", preferredStyle: .alert)
        let okAction = UIAlertAction(title: "OK", style:.default, handler: nil)
        alertVC.addAction(okAction)
        present(alertVC, animated: true, completion: nil)
    }
}
