//
//  InitialViewController.swift
//  
//
//  Created by PEPDEVILS  on 03/01/2017.
//  Copyright © 2017 PEPDEVILS . All rights reserved.
//

import UIKit
import RCActionView

class InitialViewController : UIViewController {
    
    @IBOutlet weak var InitialImage: UIImageView!
    @IBOutlet weak var InitialImageHeightConstraint: NSLayoutConstraint!
    
    override func viewWillAppear(_ animated: Bool) {
        self.view.backgroundColor = UIColor.init(red: 13/255, green: 36/255, blue: 61/255, alpha: 1)
        if UIScreen.main.bounds.width > 500
        {
            InitialImageHeightConstraint.constant = 130
        }
        let URL = "http://xxxxx.xxxx/xxx/mobile_api.php"
        let url = NSURL(string: "" + URL.addingPercentEncoding(withAllowedCharacters: CharacterSet.urlQueryAllowed)!)
        var request = URLRequest(url : url as! URL)
        let params = "method=ral_colors"
        let session = URLSession.shared
        request.httpMethod = "POST"
        request.httpBody = params.data(using: .utf8)
        let task = session.dataTask(with: request, completionHandler: {data, response, error -> Void in
            
            DispatchQueue.main.async(execute: {
                
                if data == nil
                {
                    let alertController = UIAlertController(title: "Conexão internet", message: "Verifique a sua conexão à internet", preferredStyle: .alert)
                    let CancelAction = UIAlertAction(title: "OK", style: .cancel,  handler: { _ in
                        exit(0)
                    })
                    alertController.addAction(CancelAction)
                    self.present(alertController, animated: true, completion: nil)
                }
                else
                {
                    if let json = try? JSONSerialization.jsonObject(with: data!, options: []) as! [String]{
                        //print(json)
                        if json != nil
                        {
                            let ral = json[0]
                            
                            if let ral_aux = ral.data(using: .utf8) {
                                do {
                                    GlobalVariables.ral_aux_aux = try JSONSerialization.jsonObject(with: ral_aux, options: []) as! [String: AnyObject]
                                } catch {
                                    print(error.localizedDescription)
                                }
                            }
                            
                            for d in 0..<10{
                                var nomeC : String = "RAL \(d)"
                                for c in 0..<10{
                                    nomeC = "RAL \(d)\(c)"
                                    for b in 0..<10{
                                        nomeC = "RAL \(d)\(c)\(b)"
                                        for a in 0..<10{
                                            nomeC = "RAL \(d)\(c)\(b)\(a)"
                                            
                                            let ral = GlobalVariables.ral_aux_aux[nomeC]
                                            
                                            if ral != nil
                                            {
                                                var id = ral!["rgb"] as? String
                                                if id == nil || id == "" {
                                                    id = "Indisponível"
                                                }
                                                let r = id!.components(separatedBy: ",")[0]
                                                let g = id!.components(separatedBy: ",")[1]
                                                let b = id!.components(separatedBy: ",")[2]
                                                
                                                let hexValue = String(format:"%02X", Int(r)!) + String(format:"%02X", Int(g)!) + String(format:"%02X", Int(b)!)
                                                
                                                GlobalVariables.arrayColors.append(hexValue)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        let alertController = UIAlertController(title: "Conexão internet", message: "Verifique a sua conexão à internet", preferredStyle: .alert)
                        let CancelAction = UIAlertAction(title: "OK", style: .cancel,  handler: { _ in
                            exit(0)
                        })
                        alertController.addAction(CancelAction)
                        self.present(alertController, animated: true, completion: nil)
                    }
                }
            })
        })
        task.resume()
        
        let URLDoor = "http://xxxxxxx.xxxx/xxx/mobile_api.php"
        let urlDoor = NSURL(string: "" + URLDoor.addingPercentEncoding(withAllowedCharacters: CharacterSet.urlQueryAllowed)!)
        var requestDoor = URLRequest(url : urlDoor as! URL)
        let paramsDoor = "method=all_doors"
        let sessionDoor = URLSession.shared
        requestDoor.httpMethod = "POST"
        requestDoor.httpBody = paramsDoor.data(using: .utf8)
        let taskDoor = sessionDoor.dataTask(with: requestDoor, completionHandler: {data, response, error -> Void in
            
            DispatchQueue.main.async(execute: {
                
                if data == nil
                {
                    let alertController = UIAlertController(title: "Conexão internet", message: "Verifique a sua conexão à internet", preferredStyle: .alert)
                    let CancelAction = UIAlertAction(title: "OK", style: .cancel,  handler: { _ in
                        exit(0)
                    })
                    alertController.addAction(CancelAction)
                    self.present(alertController, animated: true, completion: nil)
                }
                else
                {
                    
                    if let json = try? JSONSerialization.jsonObject(with: data!, options: []) as! NSArray {
                        let jsonResult = json.mutableCopy() as! NSMutableArray
                        print(jsonResult)
                        //print(json.count)
                        if jsonResult != nil
                        {
                            for i in 0..<jsonResult.count
                            {
                                var door = (jsonResult[i] as AnyObject).value(forKey: "image") as! String
                                door = "http://xxxxxx.xxxx/xxxxx/\(door)"
                                GlobalVariables.ArrayLink.append(door)
                                
                            }
                            print(GlobalVariables.ArrayLink)
                            
                            for i in 0..<GlobalVariables.ArrayLink.count
                            {
                                
                                
                                if GlobalVariables.ArrayLink[i].contains("New_line_series")
                                {
                                    GlobalVariables.ArrayNewLineSeries.append(GlobalVariables.ArrayLink[i])
                                }
                                
                                if GlobalVariables.ArrayLink[i].contains("Paineis_para_Portas")
                                {
                                    GlobalVariables.ArrayPaineisParaPortas.append(GlobalVariables.ArrayLink[i])
                                }
                                
                                if GlobalVariables.ArrayLink[i].contains("Paineis_Retiline")
                                {
                                    GlobalVariables.ArrayPaineisRetiline.append(GlobalVariables.ArrayLink[i])
                                }
                                
                                if GlobalVariables.ArrayLink[i].contains("Paineis_Estampados")
                                {
                                    GlobalVariables.ArrayPaineisEstampados.append(GlobalVariables.ArrayLink[i])
                                }
                                
                                if GlobalVariables.ArrayLink[i].contains("Line_Fashion")
                                {
                                    GlobalVariables.ArrayLineFashion.append(GlobalVariables.ArrayLink[i])
                                }
                            }
                            print("\n\n\n\n\n\n\n\n\n\n\nPAINEIS TODAS AS PORTAS \(GlobalVariables.ArrayPaineisEstampados)")
                            
                            GlobalVariables.ArrayLineFashion = GlobalVariables.ArrayLineFashion.sorted()
                            self.appendDoors(GlobalVariables.ArrayLineFashion)
                            GlobalVariables.ArrayPaineisEstampados = GlobalVariables.ArrayPaineisEstampados.sorted()
                            self.appendDoors(GlobalVariables.ArrayPaineisEstampados)
                            GlobalVariables.ArrayPaineisRetiline = GlobalVariables.ArrayPaineisRetiline.sorted()
                            self.appendDoors(GlobalVariables.ArrayPaineisRetiline)
                            GlobalVariables.ArrayPaineisParaPortas = GlobalVariables.ArrayPaineisParaPortas.sorted()
                            self.appendDoors(GlobalVariables.ArrayPaineisParaPortas)
                            GlobalVariables.ArrayNewLineSeries = GlobalVariables.ArrayNewLineSeries.sorted()
                            self.appendDoors(GlobalVariables.ArrayNewLineSeries)
                        }
                    } else {
                        let alertController = UIAlertController(title: "Conexão internet", message: "Verifique a sua conexão à internet", preferredStyle: .alert)
                        let CancelAction = UIAlertAction(title: "OK", style: .cancel,  handler: { _ in
                            exit(0)
                        })
                        alertController.addAction(CancelAction)
                        self.present(alertController, animated: true, completion: nil)
                    }
                }
            })
        })
        taskDoor.resume()
        
        UIView.animate(withDuration: 3, animations: {
            self.InitialImage.alpha = 1
        }, completion: { finished in
            self.performSegue(withIdentifier: "segueView", sender: self)
        })
    }
    
    func appendDoors(_ string : [String])
    {
        for i in 0..<string.count
        {
            if string[i].contains("New_line_series")
            {
                let door = string[i].components(separatedBy: "/")[8]
                
                if door.contains("_2")
                {
                    if !door.contains("l") && !door.contains("I") && !door.contains("_2L")
                    {
                        GlobalVariables.ArrayDoors.append(string[i])
                    }
                }
            }
            
            if string[i].contains("Paineis_para_Portas")
            {
                let door = string[i].components(separatedBy: "/")[8]
                
                if door == "2"
                {
                    GlobalVariables.ArrayDoors.append(string[i])
                }
            }
            
            if string[i].contains("Paineis_Retiline")
            {
                let door = string[i].components(separatedBy: "/")[8]
                
                if !door.contains("I")
                {
                    GlobalVariables.ArrayDoors.append(string[i])
                }
            }
            
            if string[i].contains("Paineis_Estampados")
            {
                let door = string[i].components(separatedBy: "/")[8]
                print(door)
                if door == "2" || door == "2200"
                {
                    GlobalVariables.ArrayDoors.append(string[i])
                }
            }
            
            if string[i].contains("Line_Fashion")
            {
                GlobalVariables.ArrayDoors.append(string[i])
                
            }
        }
    }
    override var prefersStatusBarHidden: Bool {
        return true
    }
}
