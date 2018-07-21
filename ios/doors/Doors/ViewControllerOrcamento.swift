//
//  ViewControllerOrcamento.swift
//  
//
//  Created by PEPDEVILS  on 06/01/2017.
//  Copyright © 2017 PEPDEVILS . All rights reserved.
//

import UIKit
import RCActionView

class ViewControllerOrcamento : UIViewController, UITextViewDelegate, UITextFieldDelegate {
    
    //CONSTRAINT
    @IBOutlet weak var lblHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_gobackWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_gobackHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var iconHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var iconWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var imagePreviewHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var imagePreviewWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var imageColorHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var imageColorWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var imageModelWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var imageModelHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var imagePreviewLeading: NSLayoutConstraint!
    @IBOutlet weak var imagePreviewTop: NSLayoutConstraint!
    @IBOutlet weak var lbl_orcamentoLeadingConstraint: NSLayoutConstraint!
    @IBOutlet weak var lbl_orcamentoTopConstraint: NSLayoutConstraint!
    @IBOutlet weak var lbl_orcamentoWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var lbl_orcamentoTrailingConstraint: NSLayoutConstraint!
    @IBOutlet weak var lbl_doorsTopConstraint: NSLayoutConstraint!
    @IBOutlet weak var lbl_doorsTrailingConstraint: NSLayoutConstraint!
    @IBOutlet weak var lbl_doorsLeadingConstraint: NSLayoutConstraint!
    @IBOutlet weak var lbl_doors_moradaHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var lbl_doorsHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var lbl_tlmWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var lbl_faxWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_sendWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_sendHeightConstraint: NSLayoutConstraint!
    
    
    
    
    @IBOutlet weak var ScrollView: UIScrollView!
    @IBOutlet weak var ImageView: UIImageView!
    @IBOutlet weak var ColorImageView: UIImageView!
    @IBOutlet weak var ModelImageView: UIImageView!
    @IBOutlet weak var lbl_orcamento: UILabel!
    @IBOutlet weak var txt_name: UITextField!
    @IBOutlet weak var txt_localidade: UITextField!
    @IBOutlet weak var txt_email: UITextField!
    @IBOutlet weak var txt_contacto: UITextField!
    @IBOutlet weak var txt_date: UITextField!
    @IBOutlet weak var txt_more_information: UITextView!
    @IBOutlet weak var lbl_doors: UILabel!
    @IBOutlet weak var lbl_doors_morada: UILabel!
    @IBOutlet weak var lbl_tlm: UILabel!
    @IBOutlet weak var lbl_n_tlf: UILabel!
    @IBOutlet weak var lbl_fax: UILabel!
    @IBOutlet weak var lbl_n_fax: UILabel!
    @IBOutlet weak var lbl_doors_email: UILabel!
    @IBOutlet weak var bt_send: UIButton!
    var image = UIImage()
    var imageColor = UIImage()
    var imageModel = UIImage()
    var tintColor = UIColor()
    var line : String!
    var model : String!
    var ral_color : String!
    var acabamento : String!
    @IBAction func bt_send_action(_ sender: Any) {
        
        if (txt_name.text?.isEmpty)! || (txt_email.text?.isEmpty)! || (txt_contacto.text?.isEmpty)! || (txt_date.text?.isEmpty)! || (txt_localidade.text?.isEmpty)!
        {
            let alertController = UIAlertController(title: "Pedido Orçamento", message: "Campos assinalados com (*) são de preenchimento obrigatório", preferredStyle: .alert)
            let CancelAction = UIAlertAction(title: "Voltar", style: .cancel, handler: nil)
            alertController.addAction(CancelAction)
            self.present(alertController, animated: true, completion: nil)
        }
        else {
            
            if isValidEmail(testStr: txt_email.text!)
            {
                let dateFormat : DateFormatter = DateFormatter.init()
                dateFormat.setLocalizedDateFormatFromTemplate("dd/MM/yyyy")
                let date = dateFormat.date(from: txt_date.text!)
                
                if date == nil
                {
                    let alertController = UIAlertController(title: "Pedido Orçamento", message: "Insira uma data válida (dd/mm/aaaa)", preferredStyle: .alert)
                    let CancelAction = UIAlertAction(title: "Voltar", style: .cancel, handler: nil)
                    alertController.addAction(CancelAction)
                    self.present(alertController, animated: true, completion: nil)
                }
                else {
                    
                    let dateNow = NSDate()
                    if date! < dateNow as Date
                    {
                        let calendar = NSCalendar.current
                        let componentsYear = calendar.component(.year, from: dateNow as Date)
                        let componentsMonth = calendar.component(.month, from: dateNow as Date)
                        let componentsDay = calendar.component(.day, from: dateNow as Date)
                        let year = componentsYear
                        let month = componentsMonth
                        let day = componentsDay
                        
                        let alertController = UIAlertController(title: "Pedido Orçamento", message: "A data de entrega terá de ser superior a \(day)/\(month)/\(year)", preferredStyle: .alert)
                        let CancelAction = UIAlertAction(title: "Voltar", style: .cancel, handler: nil)
                        alertController.addAction(CancelAction)
                        self.present(alertController, animated: true, completion: nil)
                    }
                    else {
                        var arrayMail : [String] = []
                        var arrayImage : [UIImage] = []
                        
                        if UIApplication.shared.canOpenURL(NSURL(string: "mailto://") as! URL) {
                            arrayMail.append("Mail")
                            arrayImage.append(UIImage(named: "mail-icon")!)
                        }
                        if UIApplication.shared.canOpenURL(NSURL(string: "googlegmail://") as! URL) {
                            arrayMail.append("Gmail")
                            arrayImage.append(UIImage(named: "gmail-icon")!)
                        }
                        
                        if UIApplication.shared.canOpenURL(NSURL(string: "ms-outlook://") as! URL) {
                            arrayMail.append("Outlook")
                            arrayImage.append(UIImage(named: "outlook-icon")!)
                        }
                        
                        if arrayMail.isEmpty
                        {
                            
                        }
                        else {
                            let fontTitillium : UIFont = UIFont(name: "Titillium-Regular", size: 13)!
                            let fontTitilliumBold : UIFont = UIFont(name: "Titillium-Semibold", size: 13)!
                            RCActionView(style: .Light).showGridMenuWithTitle(title: "Seleccione uma aplicação", itemTitles: arrayMail, images: arrayImage, font: fontTitillium, fontBold: fontTitilliumBold, selectedHandle: { (selectedOption:Int) -> Void in
                                self.doSomething(selectedOption, array: arrayMail) })
                        }
                    }
                    
                }
            }
            else {
                let alertController = UIAlertController(title: "Pedido Orçamento", message: "Insira um email válido", preferredStyle: .alert)
                let CancelAction = UIAlertAction(title: "Voltar", style: .cancel, handler: nil)
                alertController.addAction(CancelAction)
                self.present(alertController, animated: true, completion: nil)
            }
        }
    }
    
    func doSomething(_ selectedOption:Int, array : [String]){
        var more_information = ""
        if self.txt_more_information.text == "Mais informações relevantes" {
            more_information = ""
        } else {
            more_information = self.txt_more_information.text
        }
        let date = NSDate()
        let calendar = NSCalendar.current
        let componentsYear = calendar.component(.year, from: date as Date)
        let componentsMonth = calendar.component(.month, from: date as Date)
        let year = componentsYear
        let month = componentsMonth
        let df : DateFormatter = DateFormatter()
        df.locale = Locale(identifier: "pt-PT")
        let monthName = df.monthSymbols[month-1]
        let recipients = "comercial@xxxxx.xx"
        let subject = "Doors - Orçamento - \(monthName) \(year)"
        let body = "Orçamento enviado pela aplicação móvel\n\nOrçamento para a porta com:\n- linha: \(self.line!)\n- modelo: \(self.model!)\n- cor: \(self.ral_color!)\n- acabamento: \(self.acabamento!)\n\nInformação do cliente:\n- nome: \(self.txt_name.text!)\n- localidade: \(self.txt_localidade.text!)\n- email: \(self.txt_email.text!)\n- contacto telefónico: \(self.txt_contacto.text!)\n- data de entrega: \(self.txt_date.text!)\n- mais informações: \(more_information)"
        
        let bodyOutlook = "Orçamento enviado pela aplicação móvel</br></br>Orçamento para a porta com:</br>- linha: \(self.line!)</br>- modelo: \(self.model!)</br>- cor: \(self.ral_color!)</br>- acabamento: \(self.acabamento!)</br></br>Informação do cliente:</br>- nome: \(self.txt_name.text!)</br>- localidade: \(self.txt_localidade.text!)</br>- email: \(self.txt_email.text!)</br>- contacto telefónico: \(self.txt_contacto.text!)</br>- data de entrega: \(self.txt_date.text!)</br>- mais informações: \(more_information)"
        if selectedOption != 0 {
            if array[selectedOption-1] == "Mail" {
                UIApplication.shared.openURL(NSURL(string: "mailto:\(recipients)?subject=\(subject)&body=\(body)".addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!) as! URL)
            } else if array[selectedOption-1] == "Gmail" {
                UIApplication.shared.openURL(NSURL(string: "googlegmail://co?to=\(recipients)&subject=\(subject)&body=\(bodyOutlook)".addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!) as! URL)
            } else if array[selectedOption-1] == "Outlook" {
                UIApplication.shared.openURL(NSURL(string: "ms-outlook://compose?to=\(recipients)&subject=\(subject)&body=\(bodyOutlook)".addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!) as! URL)
            }
        }
    }
    
    @IBAction func go_back(_ sender: Any) {
        dismiss(animated: true, completion: nil)
    }
    
    @IBAction func editDate(_ sender: Any) {
        if txt_date.text?.characters.count == 2 || txt_date.text?.characters.count == 5
        {
            txt_date.text?.append("/")
        }
    }
    
    func isValidEmail(testStr:String) -> Bool {
        let emailRegEx = "[A-Z0-9a-z._+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}"
        
        let emailTest = NSPredicate(format:"SELF MATCHES %@", emailRegEx)
        return emailTest.evaluate(with: testStr)
    }
    
    func isValidDate(testStr:String) -> Bool {
        let dateFormat : DateFormatter = DateFormatter.init()
        dateFormat.setLocalizedDateFormatFromTemplate("dd/MM/yyyy")
        let date = dateFormat.date(from: testStr)
        
        if date == nil
        {
            return false
        }
        else {
            return true
        }
        //return emailTest.evaluate(with: testStr)
    }
    
    override func viewDidLoad() {
        ImageView.image = image
        ColorImageView.image = imageColor
        ColorImageView.tintColor = tintColor
        ModelImageView.image = imageModel
        txt_more_information.delegate = self
        txt_name.delegate = self
        txt_localidade.delegate = self
        txt_email.delegate = self
        txt_contacto.delegate = self
        txt_date.delegate = self
        
        txt_name.clearButtonMode = UITextFieldViewMode.whileEditing
        txt_localidade.clearButtonMode = UITextFieldViewMode.whileEditing
        txt_email.autocorrectionType = UITextAutocorrectionType.no
        txt_email.clearButtonMode = UITextFieldViewMode.whileEditing
        txt_contacto.clearButtonMode = UITextFieldViewMode.whileEditing
        txt_date.clearButtonMode = UITextFieldViewMode.whileEditing
        
        //txt_more_information.clearButtonMode = UITextFieldViewMode.whileEditing
        lbl_orcamento.textColor = UIColor.init(red: 16/255, green: 36/255, blue: 61/255, alpha: 1)
        lbl_doors.textColor = UIColor.init(red: 16/255, green: 36/255, blue: 61/255, alpha: 1)
        bt_send.backgroundColor = UIColor.init(red: 16/255, green: 36/255, blue: 61/255, alpha: 1)
        bt_send.layer.cornerRadius = 5
        print(bt_send.frame.origin.y + bt_send.frame.size.height + 100)
        
        let URL = "http://xxxxxx.xxxx/xxx/mobile_api.php"
        let url = NSURL(string: "" + URL.addingPercentEncoding(withAllowedCharacters: CharacterSet.urlQueryAllowed)!)
        var request = URLRequest(url : url as! URL)
        let params = "method=doors_info"
        let session = URLSession.shared
        request.httpMethod = "POST"
        request.httpBody = params.data(using: .utf8)
        let task = session.dataTask(with: request, completionHandler: {data, response, error -> Void in
            
            DispatchQueue.main.async(execute: {
                
                //let json = try! JSONSerialization.jsonObject(with: data!, options: []) as! NSMutableArray/*{*/
                if let json = try? JSONSerialization.jsonObject(with: data!, options: []) as! NSArray {//NSMutableArray
                    
                    let jsonResult = json.mutableCopy() as! NSMutableArray
                    //print(json)
                    if jsonResult != nil {
                        print(jsonResult)
                        let email = (jsonResult[0] as AnyObject).value(forKey: "email") as! String
                        let fax = (jsonResult[0] as AnyObject).value(forKey: "fax") as! String
                        let tele = (jsonResult[0] as AnyObject).value(forKey: "tel") as! String
                        let morada = (jsonResult[0] as AnyObject).value(forKey: "morada") as! String
                        
                        let paragraphStyle = NSMutableParagraphStyle()
                        paragraphStyle.lineSpacing = 5
                        
                        let text = morada
                        
                        let attrString = NSMutableAttributedString(string: text)
                        attrString.addAttribute(NSParagraphStyleAttributeName, value:paragraphStyle, range:NSMakeRange(0, attrString.length))
                        self.lbl_doors_morada.attributedText = attrString
                        
                        //self.lbl_doors_morada.text = morada
                        self.lbl_doors_email.text = email
                        self.lbl_n_fax.text = fax
                        self.lbl_n_tlf.text = tele
                    }
                } else {
                    let alertController = UIAlertController(title: "Conexão internet", message: "Verifique a sua conexão à internet", preferredStyle: .alert)
                    let CancelAction = UIAlertAction(title: "OK", style: .cancel,  handler: { _ in
                        exit(0)
                    })
                    alertController.addAction(CancelAction)
                    self.present(alertController, animated: true, completion: nil)
                }
            })
        })
        
        task.resume()

        
        txt_more_information.text = "Mais informações relevantes"
        txt_more_information.textColor = UIColor.init(red: 202/255, green: 202/255, blue: 202/255, alpha: 1)
        txt_more_information.layer.borderWidth = 0.5
        txt_more_information.layer.borderColor = UIColor.init(red: 202/255, green: 202/255, blue: 202/255, alpha: 1).cgColor
        txt_more_information.layer.cornerRadius = 5
        
        NotificationCenter.default.addObserver(self, selector: #selector(ViewControllerOrcamento.keyboardWillShow(notification:)), name: NSNotification.Name.UIKeyboardWillShow, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(ViewControllerOrcamento.keyboardWillHide(notification:)), name: NSNotification.Name.UIKeyboardWillHide, object: nil)
        
        lbl_fax.textColor = UIColor(red: 13/255, green: 36/255, blue: 61/255, alpha: 1)
        lbl_tlm.textColor = UIColor(red: 13/255, green: 36/255, blue: 61/255, alpha: 1)
        //CONSTRAINT
        
        if UIScreen.main.bounds.width > 500 {
            lblHeightConstraint.constant = 100
            bt_gobackWidthConstraint.constant = 40
            bt_gobackHeightConstraint.constant = 40
            iconWidthConstraint.constant = 90
            iconHeightConstraint.constant = 90
            print(image.size.width)
            if image.size.width >= 700 {
                imagePreviewHeightConstraint.constant = 440
                imagePreviewWidthConstraint.constant = 200
                imageColorHeightConstraint.constant = 440
                imageColorWidthConstraint.constant = 200
                imageModelHeightConstraint.constant = 440
                imageModelWidthConstraint.constant = 200
            } else {
                imagePreviewHeightConstraint.constant = 440
                imagePreviewWidthConstraint.constant = 100
                imageColorHeightConstraint.constant = 440
                imageColorWidthConstraint.constant = 100
                imageModelHeightConstraint.constant = 440
                imageModelWidthConstraint.constant = 100
            }
            
            imagePreviewLeading.isActive = false
            imagePreviewTop.constant = 50
            ImageView.translatesAutoresizingMaskIntoConstraints = false
            lbl_orcamento.translatesAutoresizingMaskIntoConstraints = false
            lbl_doors.translatesAutoresizingMaskIntoConstraints = false
            lbl_orcamentoLeadingConstraint.isActive = false
            lbl_orcamentoTopConstraint.isActive = false
            lbl_orcamentoWidthConstraint.isActive = false
            lbl_orcamentoTrailingConstraint.isActive = false
            lbl_doorsTopConstraint.isActive = false
            lbl_doorsTrailingConstraint.isActive = false
            lbl_orcamento.font = UIFont(name: lbl_orcamento.font.fontName, size: 20)
            let imageconstraint = NSLayoutConstraint(item: self.ImageView, attribute: .centerX, relatedBy: .equal, toItem: self.ScrollView, attribute: .centerX, multiplier: 1, constant: 0)
            let orcamentoconstraint = NSLayoutConstraint(item: self.lbl_orcamento, attribute: .top, relatedBy: .equal, toItem: self.ImageView, attribute: .bottom, multiplier: 1, constant: 10)
            let orcamentoLeadingconstraint = NSLayoutConstraint(item: self.lbl_orcamento, attribute: .leading, relatedBy: .equal, toItem: self.ScrollView, attribute: .leading, multiplier: 1, constant: 20)
            let orcamentoTrailingconstraint = NSLayoutConstraint(item: self.lbl_orcamento, attribute: .trailing, relatedBy: .equal, toItem: self.view, attribute: .trailing, multiplier: 1, constant: -20)
            let doorsconstraint = NSLayoutConstraint(item: self.lbl_doors, attribute: .top, relatedBy: .equal, toItem: self.bt_send, attribute: .bottom, multiplier: 1, constant: 20)
            NSLayoutConstraint.activate([imageconstraint, orcamentoconstraint, orcamentoLeadingconstraint, orcamentoTrailingconstraint, doorsconstraint])
            lbl_doors.font = UIFont(name: lbl_doors.font.fontName, size: 23)
            lbl_doors_moradaHeightConstraint.constant = 110
            lbl_doorsLeadingConstraint.constant = 20
            lbl_doors_morada.font = UIFont(name: lbl_doors_morada.font.fontName, size: 18)
            lbl_n_tlf.font = UIFont(name: lbl_n_tlf.font.fontName, size: 18)
            lbl_n_fax.font = UIFont(name: lbl_n_fax.font.fontName, size: 18)
            lbl_doors_email.font = UIFont(name: lbl_doors_email.font.fontName, size: 18)
            lbl_tlm.font = UIFont(name: lbl_tlm.font.fontName, size: 18)
            lbl_fax.font = UIFont(name: lbl_fax.font.fontName, size: 18)
            lbl_doorsHeightConstraint.constant = 20
            lbl_tlmWidthConstraint.constant = 30
            lbl_faxWidthConstraint.constant = 30
            bt_sendWidthConstraint.constant = 150
            bt_sendHeightConstraint.constant = 35
            bt_send.titleLabel?.font = UIFont(name: (bt_send.titleLabel?.font.fontName)!, size: 18)
        }
        else if UIScreen.main.bounds.width == 414{
            imagePreviewHeightConstraint.constant = 340
            imagePreviewWidthConstraint.constant = 150
            imageColorHeightConstraint.constant = 340
            imageColorWidthConstraint.constant = 150
            imageModelHeightConstraint.constant = 340
            imageModelWidthConstraint.constant = 150
            
            imagePreviewLeading.isActive = false
            imagePreviewTop.constant = 50
            ImageView.translatesAutoresizingMaskIntoConstraints = false
            lbl_orcamento.translatesAutoresizingMaskIntoConstraints = false
            lbl_doors.translatesAutoresizingMaskIntoConstraints = false
            lbl_orcamentoLeadingConstraint.isActive = false
            lbl_orcamentoTopConstraint.isActive = false
            lbl_orcamentoWidthConstraint.isActive = false
            lbl_orcamentoTrailingConstraint.isActive = false
            lbl_doorsTopConstraint.isActive = false
            lbl_doorsTrailingConstraint.isActive = false
            lbl_orcamento.font = UIFont(name: lbl_orcamento.font.fontName, size: 20)
            let imageconstraint = NSLayoutConstraint(item: self.ImageView, attribute: .centerX, relatedBy: .equal, toItem: self.ScrollView, attribute: .centerX, multiplier: 1, constant: 0)
            let orcamentoconstraint = NSLayoutConstraint(item: self.lbl_orcamento, attribute: .top, relatedBy: .equal, toItem: self.ImageView, attribute: .bottom, multiplier: 1, constant: 10)
            let orcamentoLeadingconstraint = NSLayoutConstraint(item: self.lbl_orcamento, attribute: .leading, relatedBy: .equal, toItem: self.ScrollView, attribute: .leading, multiplier: 1, constant: 20)
            let orcamentoTrailingconstraint = NSLayoutConstraint(item: self.lbl_orcamento, attribute: .trailing, relatedBy: .equal, toItem: self.view, attribute: .trailing, multiplier: 1, constant: -20)
            let doorsconstraint = NSLayoutConstraint(item: self.lbl_doors, attribute: .top, relatedBy: .equal, toItem: self.bt_send, attribute: .bottom, multiplier: 1, constant: 20)
            NSLayoutConstraint.activate([imageconstraint, orcamentoconstraint, orcamentoLeadingconstraint, orcamentoTrailingconstraint, doorsconstraint])
            lbl_doors.font = UIFont(name: lbl_doors.font.fontName, size: 23)
            lbl_doors_moradaHeightConstraint.constant = 110
            lbl_doorsLeadingConstraint.constant = 20
            lbl_doors_morada.font = UIFont(name: lbl_doors_morada.font.fontName, size: 18)
            lbl_n_tlf.font = UIFont(name: lbl_n_tlf.font.fontName, size: 18)
            lbl_n_fax.font = UIFont(name: lbl_n_fax.font.fontName, size: 18)
            lbl_doors_email.font = UIFont(name: lbl_doors_email.font.fontName, size: 18)
            lbl_tlm.font = UIFont(name: lbl_tlm.font.fontName, size: 18)
            lbl_fax.font = UIFont(name: lbl_fax.font.fontName, size: 18)
            lbl_doorsHeightConstraint.constant = 20
            lbl_tlmWidthConstraint.constant = 30
            lbl_faxWidthConstraint.constant = 30
            bt_sendWidthConstraint.constant = 150
            bt_sendHeightConstraint.constant = 35
            bt_send.titleLabel?.font = UIFont(name: (bt_send.titleLabel?.font.fontName)!, size: 18)
        } else if UIScreen.main.bounds.width > 320 && UIScreen.main.bounds.width < 500 && UIScreen.main.bounds.width != 414 {
            
            imagePreviewHeightConstraint.constant = 150
            imagePreviewWidthConstraint.constant = 66
            imageColorHeightConstraint.constant = 150
            imageColorWidthConstraint.constant = 66
            imageModelHeightConstraint.constant = 150
            imageModelWidthConstraint.constant = 66
            lbl_orcamentoWidthConstraint.constant = UIScreen.main.bounds.width - imagePreviewWidthConstraint.constant - ImageView.frame.origin.x - lbl_orcamentoLeadingConstraint.constant - lbl_orcamentoTrailingConstraint.constant - 16

        } else {
            if image.size.width >= 700 {
                imagePreviewHeightConstraint.constant = 150
                imagePreviewWidthConstraint.constant = 66
                imageColorHeightConstraint.constant = 150
                imageColorWidthConstraint.constant = 66
                imageModelHeightConstraint.constant = 150
                imageModelWidthConstraint.constant = 66
            } else {
                imagePreviewHeightConstraint.constant = 150
                imagePreviewWidthConstraint.constant = 33
                imageColorHeightConstraint.constant = 150
                imageColorWidthConstraint.constant = 33
                imageModelHeightConstraint.constant = 150
                imageModelWidthConstraint.constant = 33
            }
        }
    }
    
    override func viewDidAppear(_ animated: Bool) {
        ScrollView.contentSize.height = bt_send.frame.origin.y + bt_send.frame.size.height + 10
        
        if UIScreen.main.bounds.width > 500 {
            ScrollView.contentSize.height = lbl_doors_email.frame.origin.y + lbl_doors_email.frame.size.height + 30
        } else if UIScreen.main.bounds.width == 414 {
            ScrollView.contentSize.height = lbl_doors_email.frame.origin.y + lbl_doors_email.frame.size.height + 30
        }
    }
    
    func keyboardWillShow(notification: NSNotification) {
        if let keyboardSize = (notification.userInfo?[UIKeyboardFrameBeginUserInfoKey] as? NSValue)?.cgRectValue {
            if view.frame.origin.y == 0{
                
                if UIScreen.main.bounds.width > 500
                {
                    if txt_name.isEditing || txt_localidade.isEditing || txt_email.isEditing {
                        
                    }
                    else
                    {
                        self.view.frame.origin.y -= keyboardSize.height
                    }
                    
                }
                else
                {
                    if txt_name.isEditing || txt_localidade.isEditing || txt_email.isEditing || txt_contacto.isEditing {
                        
                    }
                    else if txt_date.isEditing
                    {
                        self.view.frame.origin.y -= keyboardSize.height/2
                    }
                    else
                    {
                        self.view.frame.origin.y -= keyboardSize.height
                    }
                }
            }
        }
    }
    
    func keyboardWillHide(notification: NSNotification) {
        if let _ = (notification.userInfo?[UIKeyboardFrameBeginUserInfoKey] as? NSValue)?.cgRectValue {
            if view.frame.origin.y != 0{
                self.view.frame.origin.y = 0
            }
        }
    }
    
    func textViewDidBeginEditing(_ textView: UITextView) {
        if textView.textColor == UIColor.init(red: 202/255, green: 202/255, blue: 202/255, alpha: 1) {
            textView.text = nil
            textView.textColor = UIColor.black
        }
    }
    
    func textViewDidEndEditing(_ textView: UITextView) {
        if textView.text.isEmpty {
            textView.text = "Mais informações relevantes"
            textView.textColor = UIColor.init(red: 202/255, green: 202/255, blue: 202/255, alpha: 1)
        }
    }
    
    func textView(_ textView: UITextView, shouldChangeTextIn range: NSRange, replacementText text: String) -> Bool {
        if(text == "\n") {
            textView.resignFirstResponder()
            return false
        }
        return true
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        self.view.endEditing(true)
        return false
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        if textField == txt_date
        {
            if string == "" {
                txt_date.text = ""
            }
            let invalidCharacters = CharacterSet(charactersIn: "0123456789/").inverted
            return string.rangeOfCharacter(from: invalidCharacters, options: [], range: string.startIndex ..< string.endIndex) == nil
        }
        
        return true
    }
    
    override var prefersStatusBarHidden: Bool {
        return true
    }
}
private var maxLengths = [UITextField: Int]()

extension UITextField {
    
    @IBInspectable var maxLength: Int {
        get {
            // 4
            guard let length = maxLengths[self] else {
                return Int.max
            }
            return length
        }
        set {
            maxLengths[self] = newValue
            // 5
            addTarget(
                self,
                action: #selector(limitLength),
                for: UIControlEvents.editingChanged
            )
        }
    }
    
    func limitLength(textField: UITextField) {
        // 6
        guard let prospectiveText = textField.text,
            prospectiveText.characters.count > maxLength
            else {
                return
        }
        
        let selection = selectedTextRange
        // 7
        let maxCharIndex = prospectiveText.index(prospectiveText.startIndex, offsetBy: maxLength)
        text = prospectiveText.substring(to: maxCharIndex)
        selectedTextRange = selection
    }
}
