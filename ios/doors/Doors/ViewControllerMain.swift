
//
//  ViewControllerMain.swift
//  
//
//  Created by PEPDEVILS  on 04/01/2017.
//  Copyright © 2017 PEPDEVILS . All rights reserved.
//

import UIKit
import RCActionView
import SDWebImage

class ViewControllerMain : UIViewController, UICollectionViewDataSource, UICollectionViewDelegate, UICollectionViewDelegateFlowLayout {

    //var refreshCtrl: UIRefreshControl!
    var tableData:[AnyObject]!
    //var task: URLSessionDownloadTask!
    //var session: URLSession!
    var cache:NSCache<AnyObject, AnyObject>!
    
    var token = ""
    
    //CONSTRAINT
    @IBOutlet weak var LabelHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_gobackHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_gobackWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var iconHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var iconWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_cleanHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_cleanWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var ViewHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_doorHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_doorWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_colorHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_colorWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_modelWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_modelHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_optionsHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_optionsWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_infoWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_infoHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var spacing_bt_door_bt_corConstraint: NSLayoutConstraint!
    @IBOutlet weak var spacing_bt_cor_bt_modelConstraint: NSLayoutConstraint!
    @IBOutlet weak var spacing_bt_model_bt_optionsConstraint: NSLayoutConstraint!
    @IBOutlet weak var spacing_bt_options_bt_infoConstraint: NSLayoutConstraint!
    @IBOutlet weak var CollectionViewHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var ViewImagesHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var ViewImagesWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var ColorCollectionViewHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var ModelCollectionViewHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var ViewOptionsHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var rodar_maisHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var rodar_menosHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var rodar_x_maisHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var rodar_x_menosHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var rodar_y_maisHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var rodar_y_menosHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_limparHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_espelhoHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var rodar_maisWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var rodar_menosWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var rodar_x_maisWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var rodar_x_menosWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var rodar_y_maisWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var rodar_y_menosWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_limparWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_espelhoWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_limparTrailing: NSLayoutConstraint!
    @IBOutlet weak var rodar_maisLeading: NSLayoutConstraint!
    @IBOutlet weak var rodar_x_maisLeading: NSLayoutConstraint!
    @IBOutlet weak var rador_y_maisLeading: NSLayoutConstraint!
    @IBOutlet weak var bt_saveWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_saveHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_orcamentoWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var bt_orcamentoHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var linhaHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var linhaWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var lbl_linhaHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var lbl_linhaWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var modeloHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var modeloWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var lbl_modeloHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var lbl_modeloWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var CorHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var CorWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var lbl_corHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var acabamentoWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var acabamentoHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var lbl_acabamentoHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var acabamentosBottom: NSLayoutConstraint!
    @IBOutlet weak var LinhaLeading: NSLayoutConstraint!
    @IBOutlet weak var LinhaBottom: NSLayoutConstraint!
    @IBOutlet weak var ModeloBottom: NSLayoutConstraint!
    @IBOutlet weak var CorBottom: NSLayoutConstraint!
    @IBOutlet weak var imagePreviewWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var imagePreviewHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var imageColorWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var imageColorHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var imageModelWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var imageModelHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var imagePreviewTop: NSLayoutConstraint!
    @IBOutlet weak var lbl_lateral_modelWidthConstraint: NSLayoutConstraint!
    @IBOutlet weak var lbl_lateral_modelHeightConstraint: NSLayoutConstraint!
    @IBOutlet weak var ModelCollectionViewWidthConstraint: NSLayoutConstraint!
    
    
    
    //LINE
    @IBOutlet weak var collectionView: UICollectionView!
    @IBOutlet weak var ImageColorPicked: UIImageView!
    var transform = CATransform3DIdentity
    var transformInitial = CATransform3DIdentity
    var SelectedDoor = ""
    var load = 0
    var loadmore = 0
    var Alldoor = false
    //COLORS
    @IBOutlet weak var MainViewColor: UIView!
    var selectedColor: UIColor = UIColor.blue
    var selectedColorHex: String = "0000FF"
    @IBOutlet weak var lbl_ral: UILabel!
    @IBOutlet weak var colorCollectionView: UICollectionView!
    var colorList = [String]() {
        didSet {
            self.colorCollectionView.reloadData()
        }
    }
    var ColorSelected : UIColor = UIColor.clear
    
    //MODEL
    @IBOutlet weak var MainViewModel: UIView!
    @IBOutlet weak var ModelCollectionView: UICollectionView!
    @IBOutlet weak var lbl_Model: UILabel!
    var ArrayModels : [String] = []
    
    //OPTIONS
    @IBOutlet weak var MainViewOptions: UIView!
    @IBOutlet weak var bt_rodar_mais: UIButton!
    @IBOutlet weak var bt_rodar_menos: UIButton!
    @IBOutlet weak var bt_rodar_x_mais: UIButton!
    @IBOutlet weak var bt_rodar_x_menos: UIButton!
    @IBOutlet weak var bt_limpar: UIButton!
    @IBOutlet weak var bt_espelho: UIButton!
    @IBOutlet weak var bt_rodar_y_mais: UIButton!
    @IBOutlet weak var bt_rodar_y_menos: UIButton!
    var rotateZ : CGFloat = 0
    var rotateZaxis : CGFloat = 0
    @IBAction func RotateRight(_ sender: Any) {
        rotateZ = 0
        rotateZ += 1.5
        rotateZaxis += 1
        if rotateZaxis <= 30
        {

            transform = CATransform3DRotate(transform, (rotateZ * CGFloat(M_PI) / 180), 0, 0, 1)
            self.ViewImages.layer.transform = transform
            //self.ImageDoorPicked.layer.transform = transform
            //self.ImageColorPicked.layer.transform = transform
        }
        else
        {
            rotateZaxis = 30
        }
    }
    @IBAction func RotateLeft(_ sender: Any) {
        rotateZ = 0
        rotateZ -= 1.5
        rotateZaxis -= 1
        if rotateZaxis >= -30
        {
            transform = CATransform3DRotate(transform, (rotateZ * CGFloat(M_PI) / 180), 0, 0, 1)
            self.ViewImages.layer.transform = transform
            //self.ImageDoorPicked.layer.transform = transform
            //self.ImageColorPicked.layer.transform = transform
        }
        else
        {
            rotateZaxis = -30
        }
    }
    var rotateX : CGFloat = 0
    var rotateXaxis : CGFloat = 0
    @IBAction func RotateXBack(_ sender: Any) {
        rotateX = 0
        rotateX += 1.5
        rotateXaxis += 1
        if rotateXaxis <= 30
        {
            transform = CATransform3DRotate(transform, (rotateX * CGFloat(M_PI) / 180), 1, 0, 0)
            self.ViewImages.layer.transform = transform
            //self.ImageDoorPicked.layer.transform = transform
            //self.ImageColorPicked.layer.transform = transform
        }
        else
        {
            rotateXaxis = 30
        }
    }
    @IBAction func RotateXFront(_ sender: Any) {
        rotateX = 0
        rotateX -= 1.5
        rotateXaxis -= 1
        if rotateXaxis >= -30
        {

            transform = CATransform3DRotate(transform, (rotateX * CGFloat(M_PI) / 180), 1, 0, 0)
            self.ViewImages.layer.transform = transform
            //self.ImageDoorPicked.layer.transform = transform
            //self.ImageColorPicked.layer.transform = transform
        }
        else
        {
            rotateXaxis = -30
        }
    }
    var rotateY : CGFloat = 0
    var rotateYaxis : CGFloat = 0
    @IBAction func RotateYFront(_ sender: Any) {
        rotateY = 0
        rotateY += 1.5
        rotateYaxis += 1
        if rotateYaxis <= 30
        {
            
            transform = CATransform3DRotate(transform, (rotateY * CGFloat(M_PI) / 180), 0, 1, 0)
            self.ViewImages.layer.transform = transform
            //self.ImageDoorPicked.layer.transform = transform
            //self.ImageColorPicked.layer.transform = transform
        }
        else
        {
            rotateYaxis = 30
        }
        
    }
    @IBAction func RotateYBack(_ sender: Any) {
        rotateY = 0
        rotateY -= 1.5
        rotateYaxis -= 1
        if rotateYaxis >= -30
        {
            transform = CATransform3DRotate(transform, (rotateY * CGFloat(M_PI) / 180), 0, 1, 0)
            self.ViewImages.layer.transform = transform
            //self.ImageDoorPicked.layer.transform = transform
            //self.ImageColorPicked.layer.transform = transform
        }
        else
        {
            rotateYaxis = -30
        }
        
    }
    @IBAction func Clean_bt(_ sender: Any) {
        ViewImages.layer.transform = transformInitial
        ImageDoorPicked.image? = UIImage(cgImage: (ImageDoorPicked.image?.cgImage)!, scale: (ImageDoorPicked.image?.scale)!, orientation: UIImageOrientation.up)
        ImageModelPicked.image? = UIImage(cgImage: (ImageModelPicked.image?.cgImage)!, scale: (ImageModelPicked.image?.scale)!, orientation: UIImageOrientation.up)
        rotateYaxis = 0
        rotateXaxis = 0
        rotateZaxis = 0
        //ImageDoorPicked.layer.transform = transformInitial
        //ImageColorPicked.layer.transform = transformInitial
        transform = transformInitial
    }
    
    var espelhoBool = false
    @IBAction func bt_espelho(_ sender: Any) {
        if !espelhoBool
        {
            ImageDoorPicked.image? = UIImage(cgImage: (ImageDoorPicked.image?.cgImage)!, scale: (ImageDoorPicked.image?.scale)!, orientation: UIImageOrientation.upMirrored)
            //ImageColorPicked.image? = UIImage(cgImage: (ImageColorPicked.image?.cgImage)!, scale: (ImageColorPicked.image?.scale)!, orientation: UIImageOrientation.upMirrored)
            ImageModelPicked.image? = UIImage(cgImage: (ImageModelPicked.image?.cgImage)!, scale: (ImageModelPicked.image?.scale)!, orientation: UIImageOrientation.upMirrored)
            espelhoBool = !espelhoBool
        }
        else
        {
            ImageDoorPicked.image? = UIImage(cgImage: (ImageDoorPicked.image?.cgImage)!, scale: (ImageDoorPicked.image?.scale)!, orientation: UIImageOrientation.up)
            //ImageColorPicked.image? = UIImage(cgImage: (ImageColorPicked.image?.cgImage)!, scale: (ImageColorPicked.image?.scale)!, orientation: UIImageOrientation.up)
            ImageModelPicked.image? = UIImage(cgImage: (ImageModelPicked.image?.cgImage)!, scale: (ImageModelPicked.image?.scale)!, orientation: UIImageOrientation.up)
            espelhoBool = !espelhoBool
        }
    }

    //INFO
    @IBOutlet weak var MainViewInfo: UIView!
    @IBOutlet weak var ViewImages: UIView!
    @IBOutlet weak var image_preview: UIImageView!
    @IBOutlet weak var image_color_preview: UIImageView!
    @IBOutlet weak var image_model_preview: UIImageView!
    @IBOutlet weak var lbl_linha: UILabel!
    @IBOutlet weak var lbl_cor: UILabel!
    @IBOutlet weak var lbl_acabamento: UILabel!
    @IBOutlet weak var lbl_modelo: UILabel!
    @IBOutlet weak var save_image: UIButton!
    @IBOutlet weak var pedir_orcamento: UIButton!
    @IBOutlet weak var Linha: UILabel!
    @IBOutlet weak var Cor: UILabel!
    @IBOutlet weak var Acabamentos: UILabel!
    @IBOutlet weak var Modelo: UILabel!
    var imageMerge : UIImage!
    let customAlbum = CustomPhotoAlbum.sharedInstance
    @IBAction func save_image_action(_ sender: Any) {
        UIGraphicsBeginImageContext(self.ViewC.bounds.size)
        self.ImagePicked.layer.render(in: UIGraphicsGetCurrentContext()!)
        self.ViewC.layer.render(in: UIGraphicsGetCurrentContext()!)
        imageMerge = UIGraphicsGetImageFromCurrentImageContext();
        UIGraphicsEndImageContext();
        customAlbum.saveImage(image: imageMerge)
        if customAlbum.assetCollection == nil {
            self.view.makeToast("Ocorreu um erro!\nPor favor, tente mais tarde!", duration: 1.0, position: self.view.center)
        } else {
            self.view.makeToast("Imagem gravada com sucesso!", duration: 1.0, position: self.view.center)
        }
    }
    
    @IBAction func pedir_orcamento_action(_ sender: Any) {
        if lbl_cor.text == "Seleccione uma cor"
        {
            self.view.makeToast("Por favor, seleccione uma cor!", duration: 1.0, position: self.view.center)
        } else {
            performSegue(withIdentifier: "segueOrcamento", sender: nil)
        }
        
    }
    
    @IBOutlet weak var ViewC: UIScrollView!
    @IBOutlet weak var ImageDoorPicked: UIImageView!
    @IBOutlet weak var ImageModelPicked: UIImageView!
    var imagetransform = CGAffineTransform()
    var ImageViewAcessorio = UIImageView()
    @IBOutlet weak var ImagePicked: UIImageView!
    var newimage = UIImage()
    var pos : CGFloat = 0
    let n_portas = 200
    @IBOutlet weak var img_indicator: UIImageView!
    @IBOutlet weak var bt_door: UIButton!
    @IBOutlet weak var bt_color: UIButton!
    @IBOutlet weak var bt_model: UIButton!
    @IBOutlet weak var bt_options: UIButton!
    @IBOutlet weak var bt_info: UIButton!
    var boolHidden : Bool = true
    var boolHidden1 : Bool = false
    var boolHidden2 : Bool = false
    var boolHidden3 : Bool = false
    var boolHidden4 : Bool = false
    var boolHidden5 : Bool = false
    var ColorEnable : Bool = false
    var LineFashion : Bool = false
    var Retiline : Bool = false
    var ModelEnable : Bool = false
    var InfoEnable : Bool = false
    var OptionsEnable : Bool = false
    @IBOutlet weak var bt_clean: UIButton!
    @IBAction func bt_clean_action(_ sender: Any) {
        UIView.animate(withDuration: 1, animations: {
            self.ImageColorPicked.alpha = 0
            self.ImageDoorPicked.alpha = 0
            self.ImageModelPicked.alpha = 0
            //self.ModelCollectionView.alpha = 0
            self.MainViewModel.alpha = 0
            self.MainViewColor.alpha = 0
            self.collectionView.alpha = 0
            self.MainViewOptions.alpha = 0
            self.MainViewInfo.alpha = 0
            self.img_indicator.alpha = 0
        }, completion: { finished in
            self.ImageColorPicked.image = UIImage(named: "Color")
            self.ImageDoorPicked.image = UIImage(named: "Color")
            self.ImageModelPicked.image = UIImage(named: "Color")
            //self.ModelCollectionView.isHidden = true
            self.MainViewModel.isHidden = true
            self.MainViewColor.isHidden = true
            self.collectionView.isHidden = true
            self.MainViewOptions.isHidden = true
            self.MainViewInfo.isHidden = true
            self.img_indicator.isHidden = true
            self.bt_color.setImage(UIImage(named: "_cores_u"), for: .normal)
            //self.bt_color.isUserInteractionEnabled = false
            self.bt_model.setImage(UIImage(named: "_materiais_u"), for: .normal)
            self.bt_options.setImage(UIImage(named: "_opcoes_u"), for: .normal)
            self.bt_info.setImage(UIImage(named: "_info_u"), for: .normal)
            //self.bt_model.isUserInteractionEnabled = false
            //self.bt_options.isEnabled = false
            //self.bt_options.isUserInteractionEnabled = false
            //self.bt_info.isEnabled = false
            //self.bt_info.isUserInteractionEnabled = false
            self.boolHidden = true
            self.boolHidden1 = false
            self.boolHidden2 = false
            self.boolHidden3 = false
            self.boolHidden4 = false
            self.boolHidden5 = false
            self.ImageColorPicked.alpha = 1
            self.ImageDoorPicked.alpha = 1
            self.ImageModelPicked.alpha = 1
            //self.ModelCollectionView.alpha = 1
            self.MainViewModel.alpha = 1
            self.MainViewColor.alpha = 1
            self.collectionView.alpha = 1
            self.MainViewOptions.alpha = 1
            self.MainViewInfo.alpha = 1
            self.img_indicator.alpha = 1
            self.ColorEnable = false
            self.ModelEnable = false
            self.OptionsEnable = false
            self.InfoEnable = false
            self.LineFashion = false
            self.Retiline = false
            self.ViewImages.layer.transform = self.transformInitial
            self.ImageDoorPicked.image? = UIImage(cgImage: (self.ImageDoorPicked.image?.cgImage)!, scale: (self.ImageDoorPicked.image?.scale)!, orientation: UIImageOrientation.up)
            self.ImageModelPicked.image? = UIImage(cgImage: (self.ImageModelPicked.image?.cgImage)!, scale: (self.ImageModelPicked.image?.scale)!, orientation: UIImageOrientation.up)
            self.transform = self.transformInitial
            self.rotateYaxis = 0
            self.rotateXaxis = 0
            self.rotateZaxis = 0
        })
        
    }
    
    @IBAction func go_back(_ sender: Any) {
        let fontTitillium : UIFont = UIFont(name: "Titillium-Regular", size: 13)!
        let fontTitilliumBold : UIFont = UIFont(name: "Titillium-Semibold", size: 13)!
        RCActionView(style: .Light).showAlertWithTitle(title: "Alerta", message: "Tem a certeza que deseja sair?\nO processo será perdido!", leftButtonTitle: "Não", rightButtonTitle: "Sim", font: fontTitillium, fontBold: fontTitilliumBold, selectedHandle: { (selectedOption:Int) -> Void in
            self.back(selectedOption: selectedOption) })
        
    }
    
    func back(selectedOption: Int)
    {
        if selectedOption == 1
        {
            self.dismiss(animated: true, completion: nil)
        }
    }
    @IBAction func bt_openURL(_ sender: Any) {
        
    }
    
    
    @IBAction func bt_door(_ sender: Any) {
        
        //ModelCollectionView.isHidden = true
        MainViewModel.isHidden = true
        MainViewColor.isHidden = true
        MainViewOptions.isHidden = true
        MainViewInfo.isHidden = true
        
        if boolHidden2 || boolHidden3 || boolHidden4 || boolHidden5
        {
            img_indicator.isHidden = false
            collectionView.isHidden = false
            bt_clean.isHidden = false
            boolHidden2 = false
            boolHidden3 = false
            boolHidden4 = false
            boolHidden5 = false
        }
        else
        {
            img_indicator.isHidden = true
            collectionView.isHidden = true
        }
        
        if boolHidden1
        {
            img_indicator.isHidden = true
            collectionView.isHidden = true
            boolHidden1 = false
            
        }
        else
        {
            img_indicator.isHidden = false
            collectionView.isHidden = false
            boolHidden1 = true

        }
        
        //img_indicator.isHidden = !img_indicator.isHidden
        img_indicator.center.x = bt_door.center.x
    }
    
//    func loadImage(_ imageString: String) -> UIImage{
//        
//        if imageString != ""
//        {
//            let url = URL(string: imageString.addingPercentEncoding(withAllowedCharacters: NSCharacterSet.urlQueryAllowed)!)
//            if (url == nil)
//            {
//                let image = UIImage(named: "porta")
//                //Loader.stopAnimating()
//                //MyGlobalVariables.DestaquesLoad = true
//                return image!
//            }
//            else
//            {
//                var image = UIImage(named: "porta")
//                DispatchQueue.global().async(execute: { 
//                    let data : Data? = try? Data(contentsOf: url!)
//                    
//                    //Loader.stopAnimating()
//                    //MyGlobalVariables.DestaquesLoad = true
//                    
//                    if data != nil
//                    {
//                        DispatchQueue.main.async(execute: { 
//                            image = UIImage(data: data!)
//                        })
//                    }
//                })
//                return image!
//            }
//        }
//        else
//        {
//            let image = UIImage(named: "porta")
//            return image!
//        }
//        
//    }

    @IBAction func bt_color(_ sender: Any) {
        
        if !ColorEnable {
            self.view.hideToastActivity()
            self.view.makeToast("Seleccione uma linha para desbloquear as cores", duration: 1.0, position: self.view.center)
        } else {
            if LineFashion {
                self.view.hideToastActivity()
                self.view.makeToast("A linha 'Line Fashion' não é personalizável", duration: 1.0, position: self.view.center)
            } else {
                if Retiline {
                    self.view.hideToastActivity()
                    self.view.makeToast("Este modelo da linha 'Retiline' não é personalizável", duration: 1.0, position: self.view.center)
                } else {
                    collectionView.isHidden = true
                    //ModelCollectionView.isHidden = true
                    MainViewModel.isHidden = true
                    MainViewOptions.isHidden = true
                    MainViewInfo.isHidden = true
                    bt_clean.isHidden = false
                    if boolHidden1 || boolHidden3 || boolHidden4 || boolHidden5
                    {
                        img_indicator.isHidden = false
                        MainViewColor.isHidden = false
                        boolHidden1 = false
                        boolHidden3 = false
                        boolHidden4 = false
                        boolHidden5 = false
                    } else {
                        img_indicator.isHidden = true
                        MainViewColor.isHidden = true
                    }
                    
                    if boolHidden2
                    {
                        img_indicator.isHidden = true
                        MainViewColor.isHidden = true
                        boolHidden2 = false
                    } else {
                        
                        if token == "true" {
                            let fontTitillium : UIFont = UIFont(name: "Titillium-Regular", size: 13)!
                            let fontTitilliumBold : UIFont = UIFont(name: "Titillium-Semibold", size: 13)!
                            RCActionView(style: .Light).showAlertWithTitle(title: "Aviso", message: "As cores que visualizar nesta apresentação são meramente indicativas, podendo não corresponder à realidade! \nDeseja que este aviso não volte a ser visualizado?", leftButtonTitle: "Não", rightButtonTitle: "Sim", font: fontTitillium, fontBold: fontTitilliumBold, selectedHandle: { (selectedOption:Int) -> Void in
                                self.cor(selectedOption: selectedOption) })
                        }
                        
                        img_indicator.isHidden = false
                        //self.showColorPicker()
                        
                        MainViewColor.isHidden = false
                        boolHidden2 = true
                    }
                    
                    //img_indicator.isHidden = !img_indicator.isHidden
                    img_indicator.center.x = bt_color.center.x
                }
            }
        }
    }
    
    func cor(selectedOption: Int)
    {
        if selectedOption == 1
        {
            token = "false"
            let defaults = UserDefaults.standard
            defaults.set(token, forKey: "AvisoCor")
            defaults.synchronize()
            //self.dismiss(animated: true, completion: nil)
        }
    }
    
    @IBAction func bt_model(_ sender: Any) {
        
        if !ModelEnable {
            self.view.hideToastActivity()
            self.view.makeToast("Seleccione uma linha para desbloquear os modelos", duration: 1.0, position: self.view.center)
        } else {
            if LineFashion {
                self.view.hideToastActivity()
                self.view.makeToast("A linha 'Line Fashion' não é personalizável", duration: 1.0, position: self.view.center)
            } else {
                collectionView.isHidden = true
                MainViewColor.isHidden = true
                MainViewOptions.isHidden = true
                MainViewInfo.isHidden = true
                bt_clean.isHidden = false
                if boolHidden2 || boolHidden1 || boolHidden4 || boolHidden5
                {
                    img_indicator.isHidden = false
                    //ModelCollectionView.isHidden = false
                    MainViewModel.isHidden = false
                    boolHidden2 = false
                    boolHidden1 = false
                    boolHidden4 = false
                    boolHidden5 = false
                }
                else
                {
                    img_indicator.isHidden = true
                    MainViewModel.isHidden = true
                    //ModelCollectionView.isHidden = true
                }
                
                if boolHidden3
                {
                    img_indicator.isHidden = true
                    
                    MainViewModel.isHidden = true
                    //ModelCollectionView.isHidden = true
                    boolHidden3 = false
                }
                else
                {
                    img_indicator.isHidden = false
                    MainViewModel.isHidden = false
                    //ModelCollectionView.isHidden = false
                    boolHidden3 = true
                }
                
                //img_indicator.isHidden = !img_indicator.isHidden
                img_indicator.center.x = bt_model.center.x
            }
        }
    }
    
    @IBAction func bt_options(_ sender: Any) {
        
        if !OptionsEnable {
            self.view.hideToastActivity()
            self.view.makeToast("Seleccione uma linha para desbloquear as opções", duration: 1.0, position: self.view.center)
        } else {
            collectionView.isHidden = true
            MainViewColor.isHidden = true
            //ModelCollectionView.isHidden = true
            MainViewModel.isHidden = true
            MainViewInfo.isHidden = true
            bt_clean.isHidden = false
            if boolHidden2 || boolHidden3 || boolHidden1 || boolHidden5
            {
                img_indicator.isHidden = false
                MainViewOptions.isHidden = false
                boolHidden2 = false
                boolHidden3 = false
                boolHidden1 = false
                boolHidden5 = false
            }
            else
            {
                img_indicator.isHidden = true
                MainViewOptions.isHidden = true
            }
            
            if boolHidden4
            {
                img_indicator.isHidden = true
                MainViewOptions.isHidden = true
                boolHidden4 = false
            }
            else
            {
                img_indicator.isHidden = false
                MainViewOptions.isHidden = false
                boolHidden4 = true
            }
            
            
            //img_indicator.isHidden = !img_indicator.isHidden
            img_indicator.center.x = bt_options.center.x
        }
        
    }
    
    @IBAction func bt_info(_ sender: Any) {
        
        if !InfoEnable {
            self.view.hideToastActivity()
            self.view.makeToast("Seleccione uma linha para desbloquear mais informações", duration: 1.0, position: self.view.center)
        } else {
            collectionView.isHidden = true
            MainViewColor.isHidden = true
            //ModelCollectionView.isHidden = true
            MainViewModel.isHidden = true
            MainViewOptions.isHidden = true
            bt_clean.isHidden = true
            
            save_image.layer.cornerRadius = 4
            save_image.backgroundColor = UIColor.init(red: 16/255, green: 36/255, blue: 61/255, alpha: 1)
            pedir_orcamento.layer.cornerRadius = 4
            pedir_orcamento.backgroundColor = UIColor.init(red: 16/255, green: 36/255, blue: 61/255, alpha: 1)
            
            Linha.textColor = UIColor.init(red: 16/255, green: 36/255, blue: 61/255, alpha: 1)
            Cor.textColor = UIColor.init(red: 16/255, green: 36/255, blue: 61/255, alpha: 1)
            Acabamentos.textColor = UIColor.init(red: 16/255, green: 36/255, blue: 61/255, alpha: 1)
            Modelo.textColor = UIColor.init(red: 16/255, green: 36/255, blue: 61/255, alpha: 1)
            
            lbl_linha.textColor = UIColor.init(red: 190/255, green: 192/255, blue: 194/255, alpha: 1)
            lbl_cor.textColor = UIColor.init(red: 190/255, green: 192/255, blue: 194/255, alpha: 1)
            lbl_acabamento.textColor = UIColor.init(red: 190/255, green: 192/255, blue: 194/255, alpha: 1)
            lbl_modelo.textColor = UIColor.init(red: 190/255, green: 192/255, blue: 194/255, alpha: 1)
            
            
            let line = SelectedDoor.components(separatedBy: "/")[6]
            if line == "Paineis_Retiline"
            {
                lbl_linha.text = "Painéis Retiline"
            }
            if line == "New_line_series"
            {
                lbl_linha.text = "New line series"
            }
            if line == "Line_Fashion"
            {
                lbl_linha.text = "Line Fashion"
            }
            
            if line == "Paineis_Estampados"
            {
                lbl_linha.text = "Painéis Estampados"
            }
            
            if line == "Paineis_para_Portas"
            {
                lbl_linha.text = "Painéis para Portas"
            }
            
            lbl_modelo.text = SelectedDoor.components(separatedBy: "/")[7]
            lbl_modelo.text = lbl_modelo.text?.replacingOccurrences(of: "_0", with: " ")
            lbl_modelo.text = lbl_modelo.text?.replacingOccurrences(of: "_", with: " ")
            lbl_modelo.text = lbl_modelo.text?.replacingOccurrences(of: "L0", with: "L")
            if line == "Line_Fashion"
            {
                lbl_acabamento.text = "Pré-definido"
            }
            else {
                
                lbl_acabamento.text = SelectedDoor.components(separatedBy: "/")[8]
                if line == "New_line_series"
                {
                    lbl_acabamento.text = lbl_acabamento.text?.components(separatedBy: "_")[1]
                    lbl_acabamento.text = lbl_acabamento.text?.uppercased()
                    lbl_acabamento.text = lbl_acabamento.text?.replacingOccurrences(of: "0", with: " ")
                }else if line == "Paineis_Retiline" {
                    var arrayofstring = (lbl_acabamento.text?.characters.map { (Character) -> Character in
                        return Character
                        })!
                    for i in 0..<arrayofstring.count {
                        if arrayofstring[i] == "0" //&& arrayofstring[i-1] == " "
                        {
                            
                            arrayofstring[i] = " "//arrayofstring[i + 1]
                            //arrayofstring[i + 1] = "\0"
                            break
                        }
                        /*if arrayofstring[i] == "0" && arrayofstring[i-1] == "L"
                        {
                            
                            arrayofstring[i] = " "
                            //arrayofstring[i + 1] = "\0"
                            break
                        }*/
                    }
                    lbl_acabamento.text = String(arrayofstring)
                    lbl_acabamento.text = lbl_acabamento.text?.lowercased()
                    //lbl_acabamento.text = lbl_acabamento.text?.components(separatedBy: "_")[1]
                    //lbl_acabamento.text = lbl_acabamento.text?.uppercased()
                    //lbl_acabamento.text = lbl_acabamento.text?.replacingOccurrences(of: "0", with: " ")
                } else if line == "Paineis_para_Portas" || line == "Paineis_Estampados" {
                    lbl_modelo.text = lbl_modelo.text?.lowercased()
                    lbl_modelo.text = lbl_modelo.text?.uppercaseFirst
                }
            }
            
            if lbl_modelo.text == "Braganca"
            {
                lbl_modelo.text = "Bragança"
            }
            
            if lbl_modelo.text == "Genova"
            {
                lbl_modelo.text = "Génova"
            }
            
            if lbl_modelo.text == "Valenca"
            {
                lbl_modelo.text = "Valença"
            }
            
            if lbl_modelo.text == "Olhao"
            {
                lbl_modelo.text = "Olhão"
            }
            
            if lbl_modelo.text == "Covilha"
            {
                lbl_modelo.text = "Covilhã"
            }
            
            if lbl_modelo.text == "Fatima"
            {
                lbl_modelo.text = "Fátima"
            }
            
            if lbl_modelo.text == "Geres"
            {
                lbl_modelo.text = "Gêres"
            }
            
            if lbl_modelo.text == "Evora"
            {
                lbl_modelo.text = "Évora"
            }
            
            if lbl_modelo.text == "Ilhavo"
            {
                lbl_modelo.text = "Ílhavo"
            }
            if (lbl_acabamento.text?.contains("I"))! || line == "Line_Fashion"
            {
                
                lbl_cor.text = "Pré-definida"
            }
            else
            {
                lbl_cor.text = lbl_ral.text
            }
            
            image_preview.image = ImageDoorPicked.image
            image_color_preview.image = ImageColorPicked.image
            image_color_preview.tintColor = ImageColorPicked.tintColor
            image_model_preview.image = ImageModelPicked.image
            
            
            if boolHidden2 || boolHidden3 || boolHidden4 || boolHidden1
            {
                img_indicator.isHidden = false
                MainViewInfo.isHidden = false
                boolHidden2 = false
                boolHidden3 = false
                boolHidden4 = false
                boolHidden1 = false
            }
            else
            {
                img_indicator.isHidden = true
                MainViewInfo.isHidden = true
            }
            
            if boolHidden5
            {
                img_indicator.isHidden = true
                MainViewInfo.isHidden = true
                boolHidden5 = false
                bt_clean.isHidden = false
            }
            else
            {
                img_indicator.isHidden = false
                MainViewInfo.isHidden = false
                boolHidden5 = true
                
            }
            
            //img_indicator.isHidden = !img_indicator.isHidden
            img_indicator.center.x = bt_info.center.x
        }
    }
    
    var imageMaxScale : CGFloat!
    var imageMinScale : CGFloat!
    var imageCurrentScale : CGFloat!
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        if collectionView == self.collectionView {
            if UIScreen.main.bounds.width > 500 {
                return CGSize(width: 90, height: 90)
            } else {
                return CGSize(width: 68, height: 68)
            }
        } else if collectionView == self.colorCollectionView {
            if UIScreen.main.bounds.width > 500 {
                return CGSize(width: 50, height: 50)
            } else {
                return CGSize(width: 30, height: 30)
            }
            
        } else {
            if UIScreen.main.bounds.width > 500 {
                return CGSize(width: 90, height: 90)
            } else {
                return CGSize(width: 68, height: 68)
            }
        }
    }
    
    override func viewDidLoad() {
        //session = URLSession.shared
        //task = URLSessionDownloadTask()
        self.tableData = []
        self.cache = NSCache()
        transform.m34 = 1.0 / 500.0
        self.ImagePicked.image = newimage
        self.ViewImages.isUserInteractionEnabled = true
        self.ViewImages.addGestureRecognizer(UIPanGestureRecognizer(target: self, action: #selector(handlePan)))
        self.ViewImages.addGestureRecognizer(UIPinchGestureRecognizer(target: self, action: #selector(handlePinch)))
        imagetransform = ViewImages.transform
        imageCurrentScale = 1
        imageMaxScale = 2
        imageMinScale = 0.7
        print(lbl_Model.frame)
        print(lbl_Model.frame.size)
        lbl_Model.transform = CGAffineTransform(rotationAngle: -CGFloat.pi / 2)
        let defaults = UserDefaults.standard
        token = defaults.string(forKey: "AvisoCor")!
        
        //let constraint = NSLayoutConstraint(item: lbl_Model, attribute: .leading, relatedBy: .equal, toItem: self.MainViewModel, attribute: .bottom, multiplier: 1, constant: 0)
        
        //NSLayoutConstraint.activate([constraint])
        
        self.ModelCollectionView.delegate = self
        self.ModelCollectionView.dataSource = self
        self.collectionView.delegate = self
        self.collectionView.dataSource = self
        self.colorCollectionView.delegate = self
        self.colorCollectionView.dataSource = self
        self.loadColorList()
        print(UIScreen.main.bounds.width)
        if UIScreen.main.bounds.width > 500 {
            LabelHeightConstraint.constant = 100
            bt_gobackHeightConstraint.constant = 40
            bt_gobackWidthConstraint.constant = 40
            iconHeightConstraint.constant = 90
            iconWidthConstraint.constant = 90
            bt_cleanHeightConstraint.constant = 38
            bt_cleanWidthConstraint.constant = 80
            //bt_clean.titleLabel?.font.withSize(100)
            bt_clean.titleLabel?.font = UIFont(name: (bt_clean.titleLabel?.font.fontName)!, size: 23)
            ViewHeightConstraint.constant = 90
            bt_doorHeightConstraint.constant = 75
            bt_doorWidthConstraint.constant = 63
            bt_colorHeightConstraint.constant = 75
            bt_colorWidthConstraint.constant = 63
            bt_modelWidthConstraint.constant = 63
            bt_modelHeightConstraint.constant = 75
            bt_optionsHeightConstraint.constant = 75
            bt_optionsWidthConstraint.constant = 63
            bt_infoWidthConstraint.constant = 63
            bt_infoHeightConstraint.constant = 75
            spacing_bt_door_bt_corConstraint.constant = -30
            spacing_bt_cor_bt_modelConstraint.constant = -40
            spacing_bt_model_bt_optionsConstraint.constant = 40
            spacing_bt_options_bt_infoConstraint.constant = 25
            CollectionViewHeightConstraint.constant = 92
            ViewImagesHeightConstraint.constant = 440
            ViewImagesWidthConstraint.constant = 200
            ColorCollectionViewHeightConstraint.constant = 148
            ModelCollectionViewHeightConstraint.constant = 92
            ViewOptionsHeightConstraint.constant = 135
            rodar_maisHeightConstraint.constant = 55
            rodar_menosHeightConstraint.constant = 55
            rodar_x_maisHeightConstraint.constant = 55
            rodar_x_menosHeightConstraint.constant = 55
            rodar_y_maisHeightConstraint.constant = 55
            rodar_y_menosHeightConstraint.constant = 55
            bt_limparHeightConstraint.constant = 55
            bt_espelhoHeightConstraint.constant = 55
            rodar_maisWidthConstraint.constant = 150
            rodar_menosWidthConstraint.constant = 150
            rodar_x_maisWidthConstraint.constant = 150
            rodar_x_menosWidthConstraint.constant = 150
            rodar_y_maisWidthConstraint.constant = 150
            rodar_y_menosWidthConstraint.constant = 150
            bt_limparWidthConstraint.constant = 150
            bt_espelhoWidthConstraint.constant = 150
            bt_limparTrailing.constant = -15
            rodar_maisLeading.constant = 15
            rodar_x_maisLeading.constant = 15
            rador_y_maisLeading.constant = 15
            bt_rodar_mais.titleLabel?.font = UIFont(name: (bt_rodar_mais.titleLabel?.font.fontName)!, size: 17)
            bt_rodar_menos.titleLabel?.font = UIFont(name: (bt_rodar_menos.titleLabel?.font.fontName)!, size: 17)
            bt_rodar_x_mais.titleLabel?.font = UIFont(name: (bt_rodar_x_mais.titleLabel?.font.fontName)!, size: 17)
            bt_rodar_x_menos.titleLabel?.font = UIFont(name: (bt_rodar_x_menos.titleLabel?.font.fontName)!, size: 17)
            bt_rodar_y_mais.titleLabel?.font = UIFont(name: (bt_rodar_y_mais.titleLabel?.font.fontName)!, size: 17)
            bt_rodar_y_menos.titleLabel?.font = UIFont(name: (bt_rodar_y_menos.titleLabel?.font.fontName)!, size: 17)
            bt_limpar.titleLabel?.font = UIFont(name: (bt_limpar.titleLabel?.font.fontName)!, size: 17)
            bt_espelho.titleLabel?.font = UIFont(name: (bt_espelho.titleLabel?.font.fontName)!, size: 17)
            
            bt_saveWidthConstraint.constant = 230
            bt_saveHeightConstraint.constant = 40
            bt_orcamentoWidthConstraint.constant = 230
            bt_orcamentoHeightConstraint.constant = 40
            save_image.titleLabel?.font = UIFont(name: (save_image.titleLabel?.font.fontName)!, size: 17)
            pedir_orcamento.titleLabel?.font = UIFont(name: (pedir_orcamento.titleLabel?.font.fontName)!, size: 17)
            
            linhaHeightConstraint.constant = 21
            linhaWidthConstraint.constant = 50
            Linha.font = UIFont(name: Linha.font.fontName, size: 17)
            lbl_linhaHeightConstraint.constant = 21
            lbl_linhaWidthConstraint.constant = 200
            lbl_linha.font = UIFont(name: lbl_linha.font.fontName, size: 17)
            modeloHeightConstraint.constant = 21
            modeloWidthConstraint.constant = 60
            Modelo.font = UIFont(name: Modelo.font.fontName, size: 17)
            lbl_modeloHeightConstraint.constant = 21
            lbl_modeloWidthConstraint.constant = 200
            lbl_modelo.font = UIFont(name: lbl_modelo.font.fontName, size: 17)
            CorHeightConstraint.constant = 21
            CorWidthConstraint.constant = 35
            Cor.font = UIFont(name: Cor.font.fontName, size: 17)
            lbl_corHeightConstraint.constant = 21
            lbl_cor.font = UIFont(name: lbl_cor.font.fontName, size: 17)
            acabamentoWidthConstraint.constant = 100
            acabamentoHeightConstraint.constant = 21
            Acabamentos.font = UIFont(name: Acabamentos.font.fontName, size: 17)
            lbl_acabamentoHeightConstraint.constant = 21
            lbl_acabamento.font = UIFont(name: lbl_acabamento.font.fontName, size: 17)
            
            acabamentosBottom.constant = -15
            LinhaLeading.constant = 15
            LinhaBottom.constant = -3
            ModeloBottom.constant = -3
            CorBottom.constant = -3
            
            imagePreviewWidthConstraint.constant = 200
            imagePreviewHeightConstraint.constant = 440
            imageModelWidthConstraint.constant = 200
            imageModelHeightConstraint.constant = 440
            imageColorWidthConstraint.constant = 200
            imageColorHeightConstraint.constant = 440
            imagePreviewTop.constant = 100
            lbl_lateral_modelWidthConstraint.constant = 92
            lbl_lateral_modelHeightConstraint.constant = 50
            lbl_Model.frame.size = CGSize(width: 92, height: 50)
            ModelCollectionViewWidthConstraint.constant = UIScreen.main.bounds.width - 50
            lbl_Model.transform = lbl_Model.transform.translatedBy(x: 21, y: -22.5)
            lbl_Model.font = UIFont(name: lbl_Model.font.fontName, size: 16)
            print(lbl_Model.frame)
            print(lbl_Model.frame.size)
            
        } else if UIScreen.main.bounds.width == 414 {
            imagePreviewTop.constant = 100
            imagePreviewWidthConstraint.constant = 150
            imagePreviewHeightConstraint.constant = 340
            imageModelWidthConstraint.constant = 150
            imageModelHeightConstraint.constant = 340
            imageColorWidthConstraint.constant = 150
            imageColorHeightConstraint.constant = 340
            ModelCollectionViewWidthConstraint.constant = UIScreen.main.bounds.width - 25
            lbl_Model.transform = lbl_Model.transform.translatedBy(x: 22.5, y: -22.5)
        } else {
            ModelCollectionViewWidthConstraint.constant = UIScreen.main.bounds.width - 25
            lbl_Model.transform = lbl_Model.transform.translatedBy(x: 22.5, y: -22.5)
            print(lbl_Model.frame)
            print(lbl_Model.frame.size)
        }
        
        if GlobalVariables.arrayColors.isEmpty
        {
            let URL = "http://e.info/app/mobile_api.php"
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
        }
        
        if GlobalVariables.ArrayLink.isEmpty
        {
            let URLDoor = "http://e.info/app/mobile_api.php"
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
                        
                        if let json = try? JSONSerialization.jsonObject(with: data!, options: []) as! NSArray//NSMutableArray
                        {
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
                                print("\n\n\n\n\n\n\n\n\n\n\nPAINEIS TODAS AS PORTAS \(GlobalVariables.ArrayDoors)")
                                
                                self.appendDoors(GlobalVariables.ArrayLineFashion)
                                self.appendDoors(GlobalVariables.ArrayPaineisEstampados)
                                self.appendDoors(GlobalVariables.ArrayPaineisRetiline)
                                self.appendDoors(GlobalVariables.ArrayPaineisParaPortas)
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
        }
        
    }
    
    func handlePan(recognizer: UIPanGestureRecognizer) {
        let translation = recognizer.translation(in: self.view)
        if let view = recognizer.view {
            view.center = CGPoint(x:view.center.x + translation.x,
                                  y:view.center.y + translation.y)
            //self.ImageDoorPicked.center = CGPoint(x:view.center.x + translation.x, y:view.center.y + translation.y)
            //self.ImageColorPicked.center = CGPoint(x:view.center.x + translation.x, y:view.center.y + translation.y)
        }
        recognizer.setTranslation(CGPoint.zero, in: self.view)
    }

    func handlePinch(recognizer: UIPinchGestureRecognizer) {
        
        if transform.m11 > 2.8 {
            if recognizer.scale < 1 {
                transform = CATransform3DScale(transform, recognizer.scale, recognizer.scale, 1)
                self.ViewImages.layer.transform = transform
                recognizer.view?.layer.transform = self.ViewImages.layer.transform
                recognizer.scale = 1
            }
        } else if transform.m11 < 0.7 {
            if recognizer.scale > 1 {
                transform = CATransform3DScale(transform, recognizer.scale, recognizer.scale, 1)
                self.ViewImages.layer.transform = transform
                recognizer.view?.layer.transform = self.ViewImages.layer.transform
                recognizer.scale = 1
            }
        } else {
            transform = CATransform3DScale(transform, recognizer.scale, recognizer.scale, 1)
            self.ViewImages.layer.transform = transform
            recognizer.view?.layer.transform = self.ViewImages.layer.transform
            recognizer.scale = 1
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "segueOrcamento" {
            let destination = segue.destination as! ViewControllerOrcamento
            destination.image = image_preview.image!
            destination.imageColor = image_color_preview.image!
            destination.tintColor = image_color_preview.tintColor
            destination.imageModel = image_model_preview.image!
            destination.line = lbl_linha.text
            destination.model = lbl_modelo.text
            destination.ral_color = lbl_cor.text
            destination.acabamento = lbl_acabamento.text
        }
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
                
                if door == "2"
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
    
    func adaptivePresentationStyleForPresentationController(controller: UIPresentationController) -> UIModalPresentationStyle {
        // show popover box for iPhone and iPad both
        return UIModalPresentationStyle.none
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        if collectionView == self.colorCollectionView
        {
            return self.colorList.count
        }
        else if collectionView == self.collectionView {
            return GlobalVariables.ArrayDoors.count
        }
        else {
            return self.ArrayModels.count
        }
        
    }
    var index0Bool : Bool = false
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        // deque reusable cell from collection view.
        if collectionView == self.colorCollectionView
        {
            
            let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "ColorCell", for: indexPath)
            
            // set cell background color from given color list
            cell.backgroundColor = self.convertHexToUIColor(hexColor: self.colorList[indexPath.row])
            
            // return cell
            return cell
        }
        else if collectionView == self.collectionView {
            let cell : CollectionViewCellDoor = collectionView.dequeueReusableCell(withReuseIdentifier: "cell", for: indexPath) as! CollectionViewCellDoor
            
            if UIScreen.main.bounds.width > 500 {
                cell.lbl_lineHeightConstraint.constant = 11
                cell.image_doorWidthConstraint.constant = 22
                cell.image_doorHeightConstraint.constant = 65
                cell.lbl_line.font = UIFont(name: cell.lbl_line.font.fontName, size: 12)
                cell.lblTipo.font = UIFont(name: cell.lblTipo.font.fontName, size: 12)
                cell.lbl_tipoHeightConstraint.constant = 25
            }
            
            if !GlobalVariables.ArrayDoors.isEmpty {
                
                var arrayofstring = [Character]()

                for x in 0..<8
                {
                    let p = GlobalVariables.ArrayDoors[indexPath.item].components(separatedBy: "/")[x]
                    if p == "Paineis_Estampados"
                    {
                        cell.lblTipo.text = "Painéis Estampados"
                    }

                    if p == "Paineis_Retiline"
                    {
                        cell.lblTipo.text = "Painéis Retiline"
                    }
                    
                    if p == "Paineis_para_Portas"
                    {
                        cell.lblTipo.text = "Painéis para Portas"
                    }
                    
                    if p == "New_line_series"
                    {
                        cell.lblTipo.text = "New line series"
                    }
                    
                    if p == "Line_Fashion"
                    {
                        cell.lblTipo.text = "Line Fashion"
                    }
                    
                    cell.lbl_line.text = GlobalVariables.ArrayDoors[indexPath.item].components(separatedBy: "/")[7]
                    cell.lbl_line.text = cell.lbl_line.text?.replacingOccurrences(of: "_", with: " ")
                    if GlobalVariables.ArrayDoors[indexPath.item].components(separatedBy: "/")[6] == "Paineis_para_Portas" || GlobalVariables.ArrayDoors[indexPath.item].components(separatedBy: "/")[6] == "Paineis_Estampados"
                    {
                        cell.lbl_line.text = cell.lbl_line.text?.lowercased()
                        cell.lbl_line.text = cell.lbl_line.text?.uppercaseFirst
                        
                    }
                    //cell.lbl_line.text = String(format: "%d", cell.lbl_line.text!)
                    arrayofstring = (cell.lbl_line.text?.characters.map { (Character) -> Character in
                        return Character
                        })!
                    
                }
                for i in 0..<arrayofstring.count {
                    if arrayofstring[i] == "0" && arrayofstring[i-1] == " "
                    {
                        
                        arrayofstring[i] = arrayofstring[i + 1]
                        arrayofstring[i + 1] = "\0"
                        break
                    }
                    if arrayofstring[i] == "0" && arrayofstring[i-1] == "L"
                    {
                        
                        arrayofstring[i] = " "
                        //arrayofstring[i + 1] = "\0"
                        break
                    }
                }
                cell.lbl_line.text = String(arrayofstring)
                if cell.lbl_line.text == "Braganca"
                {
                    cell.lbl_line.text = "Bragança"
                }
                
                if cell.lbl_line.text == "Genova"
                {
                    cell.lbl_line.text = "Génova"
                }
                
                if cell.lbl_line.text == "Valenca"
                {
                    cell.lbl_line.text = "Valença"
                }
                
                if cell.lbl_line.text == "Olhao"
                {
                    cell.lbl_line.text = "Olhão"
                }
                
                if cell.lbl_line.text == "Covilha"
                {
                    cell.lbl_line.text = "Covilhã"
                }
                
                if cell.lbl_line.text == "Fatima"
                {
                    cell.lbl_line.text = "Fátima"
                }
                
                if cell.lbl_line.text == "Geres"
                {
                    cell.lbl_line.text = "Gêres"
                }
                
                if cell.lbl_line.text == "Evora"
                {
                    cell.lbl_line.text = "Évora"
                }
                
                if cell.lbl_line.text == "Ilhavo"
                {
                    cell.lbl_line.text = "Ílhavo"
                }
                
                var arrayDoorThumb = GlobalVariables.ArrayDoors[indexPath.item].components(separatedBy: "/")
                var door = arrayDoorThumb.last
                door = door!.replacingOccurrences(of: ".jpg", with: "_thumb.jpg")
                arrayDoorThumb.removeLast()
                arrayDoorThumb.append(door!)
                let stringItem = arrayDoorThumb.joined(separator: "/")
                
                let url = URL(string: /*GlobalVariables.ArrayDoors[indexPath.item]*/stringItem.addingPercentEncoding(withAllowedCharacters: NSCharacterSet.urlQueryAllowed)!)
                cell.ImageDoor.sd_setImage(with: url, placeholderImage: UIImage(named: "Color"))
            } else {
                let alertController = UIAlertController(title: "doors", message: "Verifique a sua conexão à internet e volte a iniciar a aplicação.", preferredStyle: .alert)
                let CancelAction = UIAlertAction(title: "OK", style: .cancel,  handler: { _ in
                    exit(0)
                })
                alertController.addAction(CancelAction)
                self.present(alertController, animated: true, completion: nil)
            }
            // return cell
            return cell
        }
        else {
            let cell : CollectionViewCellDoor = collectionView.dequeueReusableCell(withReuseIdentifier: "cellModel", for: indexPath) as! CollectionViewCellDoor
            
            ArrayModels = ArrayModels.sorted()
            if UIScreen.main.bounds.width > 500 {
                cell.lbl_lineHeightConstraint.constant = 11
                cell.image_doorWidthConstraint.constant = 22
                cell.image_doorHeightConstraint.constant = 65
                cell.lbl_line.font = UIFont(name: cell.lbl_line.font.fontName, size: 12)
                cell.lblTipo.font = UIFont(name: cell.lblTipo.font.fontName, size: 12)
                cell.lbl_tipoHeightConstraint.constant = 25
            }
            
            var arrayofstring = [Character]()
            var arrayofstringLine = [Character]()
            for x in 0..<8
            {
                let p = ArrayModels[indexPath.item].components(separatedBy: "/")[x]
                if p == "Paineis_Estampados"
                {
                    self.lbl_Model.text = "Painéis Estampados"
                }
                
                if p == "Paineis_Retiline"
                {
                    self.lbl_Model.text = "Painéis Retiline"
                }
                
                if p == "Paineis_para_Portas"
                {
                    self.lbl_Model.text = "Painéis para Portas"
                }
                
                if p == "New_line_series"
                {
                    self.lbl_Model.text = "New line series"
                }
                
                if p == "Line_Fashion"
                {
                    self.lbl_Model.text = "Line Fashion"
                }
                cell.lblTipo.text = ArrayModels[indexPath.item].components(separatedBy: "/")[7]
                cell.lblTipo.text = cell.lblTipo.text?.replacingOccurrences(of: "_", with: " ")
                
                cell.lbl_line.text = ArrayModels[indexPath.item].components(separatedBy: "/")[8]
                
                if p == "New line series"
                {
                    cell.lbl_line.text = cell.lbl_line.text?.components(separatedBy: "_")[1]
                    cell.lbl_line.text = cell.lbl_line.text?.uppercased()
                    
                } else {
                    cell.lbl_line.text = cell.lbl_line.text?.replacingOccurrences(of: "_", with: " ")
                }
                
                if ArrayModels[indexPath.item].components(separatedBy: "/")[6] == "Paineis_para_Portas" || ArrayModels[indexPath.item].components(separatedBy: "/")[6] == "Paineis_Estampados"
                {
                    cell.lblTipo.text = cell.lblTipo.text?.lowercased()
                    cell.lblTipo.text = cell.lblTipo.text?.uppercaseFirst
                    print(cell.lblTipo.text!)
                } else if ArrayModels[indexPath.item].components(separatedBy: "/")[6] == "Paineis_Retiline" {
                    cell.lbl_line.text = cell.lbl_line.text?.lowercased()
                }
                
                
                arrayofstring = (cell.lbl_line.text?.characters.map { (Character) -> Character in
                    return Character
                })!
                
                
                arrayofstringLine = (cell.lblTipo.text?.characters.map { (Character) -> Character in
                    return Character
                })!
                
            }
            
            for i in 0..<arrayofstringLine.count {
                if arrayofstringLine[i] == "0" && arrayofstringLine[i-1] == " "
                {
                    
                    arrayofstringLine[i] = arrayofstringLine[i + 1]
                    arrayofstringLine[i + 1] = "\0"
                    break
                }
                if arrayofstringLine[i] == "0" && arrayofstringLine[i-1] == "L"
                {
                    
                    arrayofstringLine[i] = " "
                    //arrayofstring[i + 1] = "\0"
                    break
                }
            }
            
            cell.lblTipo.text = String(arrayofstringLine)

            if cell.lblTipo.text == "Braganca"
            {
                cell.lblTipo.text = "Bragança"
            }
            
            if cell.lblTipo.text == "Genova"
            {
                cell.lblTipo.text = "Génova"
            }
            
            if cell.lblTipo.text == "Valenca"
            {
                cell.lblTipo.text = "Valença"
            }
            
            if cell.lblTipo.text == "Olhao"
            {
                cell.lblTipo.text = "Olhão"
            }
            
            if cell.lblTipo.text == "Covilha"
            {
                cell.lblTipo.text = "Covilhã"
            }

            if cell.lblTipo.text == "Fatima"
            {
                cell.lblTipo.text = "Fátima"
            }
            
            if cell.lblTipo.text == "Geres"
            {
                cell.lblTipo.text = "Gêres"
            }
            
            if cell.lblTipo.text == "Evora"
            {
                cell.lblTipo.text = "Évora"
            }
            
            if cell.lblTipo.text == "Ilhavo"
            {
                cell.lblTipo.text = "Ílhavo"
            }
            
            for i in 0..<arrayofstring.count {
//                if arrayofstring[i] == "0" && arrayofstring[i-1] == " "
//                {
//                    
//                    arrayofstring[i] = arrayofstring[i + 1]
//                    arrayofstring[i + 1] = "\0"
//                    break
//                }
                if arrayofstring[i] == "0"// && arrayofstring[i-1] == "L"
                {
                    
                    arrayofstring[i] = " "
                    //arrayofstring[i + 1] = "\0"
                    break
                }
            }
            //print(arrayofstringLine)
            if ArrayModels[indexPath.item].components(separatedBy: "/")[7] != "Delarte"
            {
                cell.lbl_line.text = String(arrayofstring)
            }
//            if self.lbl_Model.text == "New line series" {
//                cell.lbl_line.text = cell.lbl_line.text?.replacingOccurrences(of: ArrayModels[indexPath.item].components(separatedBy: "/")[7], with: "")
//            }
            // = ArrayModels[indexPath.item].components(separatedBy: "/")[6]
            //self.lbl_Model.frame.size = CGSize(width: 20, height: 70)
            var arrayModelThumb = ArrayModels[indexPath.item].components(separatedBy: "/")
            var door = arrayModelThumb.last
            door = door!.replacingOccurrences(of: ".jpg", with: "_thumb.jpg")
            arrayModelThumb.removeLast()
            arrayModelThumb.append(door!)
            let stringItem = arrayModelThumb.joined(separator: "/")
            let url = URL(string: /*ArrayModels[indexPath.item]*/stringItem.addingPercentEncoding(withAllowedCharacters: NSCharacterSet.urlQueryAllowed)!)
            cell.ImageDoor.sd_setImage(with: url, placeholderImage: UIImage(named: "Color"))
            
            return cell
        }
        
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        
        if collectionView == self.colorCollectionView
        {
            // find clicked color value from colorList (it wil be in hex)
            let clickedHexColor = self.colorList[indexPath.row]
            
            // convert hex color to UI Color
            let clickedUIColor = self.convertHexToUIColor(hexColor: clickedHexColor)
            
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
                                var rgb = ral!["rgb"] as? String
                                if rgb == nil || rgb == "" {
                                    rgb = "Indisponível"
                                }
                                
                                var colorname = ral!["name"] as? String
                                if colorname == nil || colorname == "" {
                                    colorname = "Indisponível"
                                }
                                let r = rgb!.components(separatedBy: ",")[0]
                                let g = rgb!.components(separatedBy: ",")[1]
                                let b = rgb!.components(separatedBy: ",")[2]
                                
                                let hexValue = String(format:"%02X", Int(r)!) + String(format:"%02X", Int(g)!) + String(format:"%02X", Int(b)!)
                                
                                if clickedHexColor == hexValue
                                {
                                    self.lbl_ral.text = nomeC
                                }
                            }
                        }
                    }
                }
            }
            
            ImageColorPicked.image = ImageDoorPicked.image
            ImageColorPicked.image = ImageColorPicked.image?.withRenderingMode(.alwaysTemplate)
            ImageColorPicked.tintColor = clickedUIColor.withAlphaComponent(0.7)
            ColorSelected = clickedUIColor.withAlphaComponent(0.7)
            //ImageDoorPicked.addSubview(ImageColorPicked)
            //ImageColorPicked.isHidden = true

        }
        else if collectionView == self.collectionView {
            
            let url = URL(string: GlobalVariables.ArrayDoors[indexPath.item].addingPercentEncoding(withAllowedCharacters: NSCharacterSet.urlQueryAllowed)!)
            //sd_setImage(with: url, placeholderImage: UIImage(named: "Color"))
            
            self.ImageDoorPicked.sd_setImage(with: url) { (image, error, imageCacheType, imageUrl) in
                if image != nil {
                    print(self.ImageDoorPicked.image!.size)
                    if (self.ImageDoorPicked.image?.size.width)! < CGFloat(700) {
                        if UIScreen.main.bounds.width > 500
                        {
                            self.imagePreviewWidthConstraint.constant = 100
                            self.imageModelWidthConstraint.constant = 100
                            self.imageColorWidthConstraint.constant = 100
                            self.ViewImagesWidthConstraint.constant = 100
                        } else if UIScreen.main.bounds.width == 414 {
                            self.imagePreviewWidthConstraint.constant = 150
                            self.imageModelWidthConstraint.constant = 150
                            self.imageColorWidthConstraint.constant = 150
                        } else {
                            self.ViewImagesWidthConstraint.constant = 50
                            self.imagePreviewWidthConstraint.constant = 50
                            self.imageModelWidthConstraint.constant = 50
                            self.imageColorWidthConstraint.constant = 50
                        }
                    } else {
                        if UIScreen.main.bounds.width > 500
                        {
                            self.ViewImagesWidthConstraint.constant = 200
                            self.imagePreviewWidthConstraint.constant = 200
                            self.imageModelWidthConstraint.constant = 200
                            self.imageColorWidthConstraint.constant = 200
                        } else if UIScreen.main.bounds.width == 414 {
                            self.imagePreviewWidthConstraint.constant = 150
                            self.imageModelWidthConstraint.constant = 150
                            self.imageColorWidthConstraint.constant = 150
                        } else {
                            self.ViewImagesWidthConstraint.constant = 100
                            self.imagePreviewWidthConstraint.constant = 100
                            self.imageModelWidthConstraint.constant = 100
                            self.imageColorWidthConstraint.constant = 100
                        }
                    }
                    //print("image found")
                }else
                {
                    print("image not found")
                }
            }
            //print(self.ImageDoorPicked.image!.size)
            //.sd_setImage(with: url, placeholderImage: UIImage(named: "Color"))
            //print(self.ImageDoorPicked.image!.size)
//            if (self.ImageDoorPicked.image?.size.width)! < CGFloat(700) {
//                if UIScreen.main.bounds.width > 500
//                {
//                    ViewImagesWidthConstraint.constant = 100
//                } else {
//                    ViewImagesWidthConstraint.constant = 50
//                }
//            }
            ViewImages.center = view.center
            ViewImages.center.y = view.center.y - ViewImages.frame.size.height/2
            transformInitial = ViewImages.layer.transform
            ImageColorPicked.image = UIImage(named: "Color")
            ColorSelected = UIColor.clear
            lbl_ral.text = "Seleccione uma cor"
            ArrayModels.removeAll()
            let ArrayModel = GlobalVariables.ArrayDoors[indexPath.item].components(separatedBy: "/")
            var imageString = String()
            for i in 0..<ArrayModel.count
            {
                imageString += ArrayModel[i] + "/"
                if i == 7
                {
                    break
                }
            }
            
            bt_color.setImage(UIImage(named: "_cores"), for: .normal)
            //bt_color.isUserInteractionEnabled = true
            bt_model.setImage(UIImage(named: "_material"), for: .normal)
            bt_options.setImage(UIImage(named: "_opcoes"), for: .normal)
            bt_info.setImage(UIImage(named: "_info"), for: .normal)
            //bt_model.isUserInteractionEnabled = true
            //bt_options.isEnabled = true
            //bt_options.isUserInteractionEnabled = true
            //bt_info.isEnabled = true
            //bt_info.isUserInteractionEnabled = true
            ColorEnable = true
            ModelEnable = true
            OptionsEnable = true
            InfoEnable = true
            LineFashion = false
            Retiline = false
            for i in 0..<GlobalVariables.ArrayLink.count
            {
                if GlobalVariables.ArrayLink[i].contains(imageString)
                {
                    var ArrayP = GlobalVariables.ArrayLink[i].components(separatedBy: "/")
                    
                    let stringModel = ArrayP.joined(separator: "/")
                    //print(stringModel)
                    for x in 0..<ArrayP.count
                    {
                        
                        if ArrayP[x] == "Paineis_Retiline" {
                            
                            let door = ArrayP[x+2]
                            if !door.contains("I") {
                                ArrayP.removeLast()
                                ArrayP.append("01-item.png")
                                let stringItem = ArrayP.joined(separator: "/")
                                let url = URL(string: stringItem.addingPercentEncoding(withAllowedCharacters: NSCharacterSet.urlQueryAllowed)!)
                                ImageModelPicked.sd_setImage(with: url, placeholderImage: UIImage(named: "Color"))
                                //ImageModelPicked.image = loadImage(stringItem)
                            }
                            ArrayModels.append(stringModel)
                        }
                        
                        if ArrayP[x] == "New_line_series" {
                            
                            let door = ArrayP[x+2]
                            if door.contains("_2") {
                                if !door.contains("l")
                                {
                                    ArrayP.removeLast()
                                    ArrayP.append("01-item.png")
                                    let stringItem = ArrayP.joined(separator: "/")
                                    let url = URL(string: stringItem.addingPercentEncoding(withAllowedCharacters: NSCharacterSet.urlQueryAllowed)!)
                                    ImageModelPicked.sd_setImage(with: url, placeholderImage: UIImage(named: "Color"))
                                    
                                    //ImageModelPicked.image = loadImage(stringItem)
                                }
                            }
                            ArrayModels.append(stringModel)
                        }
                        
                        if ArrayP[x] == "Line_Fashion" {
                            bt_color.setImage(UIImage(named: "_cores_u"), for: .normal)
                            //bt_color.isUserInteractionEnabled = false
                            //ColorEnable = false
                            //ModelEnable = false
                            bt_model.setImage(UIImage(named: "_materiais_u"), for: .normal)
                            //bt_model.isUserInteractionEnabled = false
                            ImageModelPicked.image = UIImage(named: "Color")
                            LineFashion = true
                        }
                        
                        if ArrayP[x] == "Paineis_Estampados" {
                            
                            let door = ArrayP[x+2]
                            if door == "2" || door == "2200" {
                                //if !door.contains("l")
                                //{
                                    ArrayP.removeLast()
                                    ArrayP.append("01-item.png")
                                    let stringItem = ArrayP.joined(separator: "/")
                                    let url = URL(string: stringItem.addingPercentEncoding(withAllowedCharacters: NSCharacterSet.urlQueryAllowed)!)
                                    ImageModelPicked.sd_setImage(with: url, placeholderImage: UIImage(named: "Color"))
                                    //ImageModelPicked.image = loadImage(stringItem)
                                //}
                            }
                            ArrayModels.append(stringModel)
                        }
                        
                        if ArrayP[x] == "Paineis_para_Portas" {
                            
                            let door = ArrayP[x+2]
                            if door == "2" {
                                //if !door.contains("l")
                                //{
                                ArrayP.removeLast()
                                ArrayP.append("01-item.png")
                                let stringItem = ArrayP.joined(separator: "/")
                                let url = URL(string: stringItem.addingPercentEncoding(withAllowedCharacters: NSCharacterSet.urlQueryAllowed)!)
                                ImageModelPicked.sd_setImage(with: url, placeholderImage: UIImage(named: "Color"))
                                //ImageModelPicked.image = loadImage(stringItem)
                                //}
                            }
                            ArrayModels.append(stringModel)
                        }
                    }
                }
            }
            SelectedDoor = GlobalVariables.ArrayDoors[indexPath.item]
            
            self.ModelCollectionView.reloadData()
            print(ArrayModels)
            img_indicator.isHidden = true
            self.collectionView.isHidden = true
            boolHidden1 = false
            //ImageModelPicked.image = UIImage(named: "Color")
            //print(GlobalVariables.ArrayPaineisRetiline)
        }
        else {
            let cell : CollectionViewCellDoor = ModelCollectionView.cellForItem(at: indexPath) as! CollectionViewCellDoor
            
            
            var ArrayP = ArrayModels[indexPath.item].components(separatedBy: "/")
            let model = ArrayP[6]
            if model == "Paineis_Retiline"
            {
                
                let door = ArrayP[8]
                if !door.contains("I")
                {
                    SelectedDoor = ArrayModels[indexPath.item]
                    let stringDoor = ArrayP.joined(separator: "/")
                    let url = URL(string: stringDoor.addingPercentEncoding(withAllowedCharacters: NSCharacterSet.urlQueryAllowed)!)
                    ImageDoorPicked.sd_setImage(with: url, placeholderImage: UIImage(named: "Color"))
                    //ImageDoorPicked.image = loadImage(stringDoor)
                    //ImageColorPicked.image = ImageDoorPicked.image
                    ImageColorPicked.image = ImageColorPicked.image?.withRenderingMode(.alwaysTemplate)
                    ImageColorPicked.tintColor = ColorSelected
                    ArrayP.removeLast()
                    ArrayP.append("01-item.png")
                    let stringItem = ArrayP.joined(separator: "/")
                    let urlModel = URL(string: stringItem.addingPercentEncoding(withAllowedCharacters: NSCharacterSet.urlQueryAllowed)!)
                    ImageModelPicked.sd_setImage(with: urlModel, placeholderImage: UIImage(named: "Color"))
                    //ImageModelPicked.image = loadImage(stringItem)
                    bt_color.setImage(UIImage(named: "_cores"), for: .normal)
                    //bt_color.isUserInteractionEnabled = true
                    ColorEnable = true
                    Retiline = false
                }
                else
                {
                    SelectedDoor = ArrayModels[indexPath.item]
                    let stringItem = ArrayP.joined(separator: "/")
                    let url = URL(string: stringItem.addingPercentEncoding(withAllowedCharacters: NSCharacterSet.urlQueryAllowed)!)
                    ImageDoorPicked.sd_setImage(with: url, placeholderImage: UIImage(named: "Color"))
                    //ImageDoorPicked.image = loadImage(stringItem)
                    ImageColorPicked.image = UIImage(named: "Color")
                    ImageModelPicked.image = UIImage(named: "Color")
                    bt_color.setImage(UIImage(named: "_cores_u"), for: .normal)
                    //bt_color.isUserInteractionEnabled = false
                    //ColorEnable = false
                    Retiline = true
                }
            }
            
            if model == "New_line_series"
            {
                SelectedDoor = ArrayModels[indexPath.item]
                let stringDoor = ArrayP.joined(separator: "/")
                let url = URL(string: stringDoor.addingPercentEncoding(withAllowedCharacters: NSCharacterSet.urlQueryAllowed)!)
                ImageDoorPicked.sd_setImage(with: url, placeholderImage: UIImage(named: "Color"))
                //ImageDoorPicked.image = loadImage(stringDoor)
                //ImageColorPicked.image = ImageDoorPicked.image
                ImageColorPicked.image = ImageColorPicked.image?.withRenderingMode(.alwaysTemplate)
                ImageColorPicked.tintColor = ColorSelected
                ArrayP.removeLast()
                ArrayP.append("01-item.png")
                let stringItem = ArrayP.joined(separator: "/")
                let urlModel = URL(string: stringItem.addingPercentEncoding(withAllowedCharacters: NSCharacterSet.urlQueryAllowed)!)
                ImageModelPicked.sd_setImage(with: urlModel, placeholderImage: UIImage(named: "Color"))
                //ImageModelPicked.image = loadImage(stringItem)
                bt_color.setImage(UIImage(named: "_cores"), for: .normal)
                //bt_color.isUserInteractionEnabled = true
                ColorEnable = true
            }
            
            if model == "Paineis_Estampados"
            {
                SelectedDoor = ArrayModels[indexPath.item]
                let stringDoor = ArrayP.joined(separator: "/")
                let url = URL(string: stringDoor.addingPercentEncoding(withAllowedCharacters: NSCharacterSet.urlQueryAllowed)!)
                ImageDoorPicked.sd_setImage(with: url, placeholderImage: UIImage(named: "Color"))
                //ImageDoorPicked.image = loadImage(stringDoor)
                
                ArrayP.removeLast()
                ArrayP.append("01-item.png")
                let stringItem = ArrayP.joined(separator: "/")
                let urlModel = URL(string: stringItem.addingPercentEncoding(withAllowedCharacters: NSCharacterSet.urlQueryAllowed)!)
                ImageModelPicked.sd_setImage(with: urlModel, placeholderImage: UIImage(named: "Color"))
                //ImageColorPicked.image = ImageDoorPicked.image
                ImageColorPicked.image = ImageColorPicked.image?.withRenderingMode(.alwaysTemplate)
                ImageColorPicked.tintColor = ColorSelected
                //ImageModelPicked.image = loadImage(stringItem)
                bt_color.setImage(UIImage(named: "_cores"), for: .normal)
                //bt_color.isUserInteractionEnabled = true
                
                ColorEnable = true
            }
            
            if model == "Paineis_para_Portas"
            {
                SelectedDoor = ArrayModels[indexPath.item]
                let stringDoor = ArrayP.joined(separator: "/")
                let url = URL(string: stringDoor.addingPercentEncoding(withAllowedCharacters: NSCharacterSet.urlQueryAllowed)!)
                ImageDoorPicked.sd_setImage(with: url, placeholderImage: UIImage(named: "Color"))
                //ImageDoorPicked.image = loadImage(stringDoor)
                //ImageColorPicked.image = ImageDoorPicked.image
                ImageColorPicked.image = ImageColorPicked.image?.withRenderingMode(.alwaysTemplate)
                ImageColorPicked.tintColor = ColorSelected
                ArrayP.removeLast()
                ArrayP.append("01-item.png")
                let stringItem = ArrayP.joined(separator: "/")
                let urlModel = URL(string: stringItem.addingPercentEncoding(withAllowedCharacters: NSCharacterSet.urlQueryAllowed)!)
                ImageModelPicked.sd_setImage(with: urlModel, placeholderImage: UIImage(named: "Color"))
                //ImageModelPicked.image = loadImage(stringItem)
                bt_color.setImage(UIImage(named: "_cores"), for: .normal)
                //bt_color.isUserInteractionEnabled = true
                ColorEnable = true
            }
        }
    }
    
    fileprivate func loadColorList(){
        if !GlobalVariables.arrayColors.isEmpty {
            self.colorList = GlobalVariables.arrayColors
        } else {
            let alertController = UIAlertController(title: "doors", message: "Verifique a sua conexão à internet e volte a iniciar a aplicação.", preferredStyle: .alert)
            let CancelAction = UIAlertAction(title: "OK", style: .cancel,  handler: { _ in
                exit(0)
            })
            alertController.addAction(CancelAction)
            self.present(alertController, animated: true, completion: nil)
        }
        
    }
    
    fileprivate func convertHexToUIColor(hexColor : String) -> UIColor {
        
        // define character set (include whitespace, newline character etc.)
        let characterSet = CharacterSet.whitespacesAndNewlines as CharacterSet
        
        //trim unnecessary character set from string
        var colorString : String = hexColor.trimmingCharacters(in: characterSet)
        
        // convert to uppercase
        colorString = colorString.uppercased()
        
        //if # found at start then remove it.
        if colorString.hasPrefix("#") {
            colorString =  colorString.substring(from: colorString.characters.index(colorString.startIndex, offsetBy: 1))
        }
        
        // hex color must 6 chars. if invalid character count then return black color.
        if colorString.characters.count != 6 {
            return UIColor.black
        }
        
        // split R,G,B component
        var rgbValue: UInt32 = 0
        Scanner(string:colorString).scanHexInt32(&rgbValue)
        let valueRed    = CGFloat((rgbValue & 0xFF0000) >> 16) / 255.0
        let valueGreen  = CGFloat((rgbValue & 0x00FF00) >> 8) / 255.0
        let valueBlue   = CGFloat(rgbValue & 0x0000FF) / 255.0
        let valueAlpha  = CGFloat(1.0)
        
        // return UIColor
        return UIColor(red: valueRed, green: valueGreen, blue: valueBlue, alpha: valueAlpha)
    }
    
    override var prefersStatusBarHidden: Bool {
        return true
    }
}

extension UIImage {
    
    func maskWithColor(color: UIColor) -> UIImage? {
        let maskImage = cgImage!
        
        let width = size.width
        let height = size.height
        let bounds = CGRect(x: 0, y: 0, width: width, height: height)
        
        let colorSpace = CGColorSpaceCreateDeviceRGB()
        let bitmapInfo = CGBitmapInfo(rawValue: CGImageAlphaInfo.premultipliedLast.rawValue)
        let context = CGContext(data: nil, width: Int(width), height: Int(height), bitsPerComponent: 8, bytesPerRow: 0, space: colorSpace, bitmapInfo: bitmapInfo.rawValue)!
        
        context.clip(to: bounds, mask: maskImage)
        context.setFillColor(color.cgColor)
        context.fill(bounds)
        
        if let cgImage = context.makeImage() {
            let coloredImage = UIImage(cgImage: cgImage)
            return coloredImage
        } else {
            return nil
        }
    }
    
}

extension String {
    var first: String {
        return String(characters.prefix(1))
    }
    var last: String {
        return String(characters.suffix(1))
    }
    var uppercaseFirst: String {
        return first.uppercased() + String(characters.dropFirst())
    }
}
