//
//  CustomPhotoAlbum.swift
//  
//
//  Created by PEPDEVILS  on 03/01/2017.
//  Copyright © 2017 PEPDEVILS . All rights reserved.
//

import Photos

class CustomPhotoAlbum: NSObject {
    static let albumName = "Doors"
    static let sharedInstance = CustomPhotoAlbum()
    
    var assetCollection: PHAssetCollection!
    
    override init() {
        super.init()
        
        if let assetCollection = fetchAssetCollectionForAlbum() {
            self.assetCollection = assetCollection
            return
        }
        
        if PHPhotoLibrary.authorizationStatus() != PHAuthorizationStatus.authorized {
            PHPhotoLibrary.requestAuthorization({ (status: PHAuthorizationStatus) -> Void in
                status
            })
        }
        
        if PHPhotoLibrary.authorizationStatus() == PHAuthorizationStatus.authorized {
            self.createAlbum()
        } else {
            PHPhotoLibrary.requestAuthorization(requestAuthorizationHandler)
        }
    }
    
    func requestAuthorizationHandler(status: PHAuthorizationStatus) {
        if PHPhotoLibrary.authorizationStatus() == PHAuthorizationStatus.authorized {
            // ideally this ensures the creation of the photo album even if authorization wasn't prompted till after init was done
            print("trying again to create the album")
            self.createAlbum()
        } else {
            print("should really prompt the user to let them know it's failed")
        }
    }
    
    func createAlbum() {
        DispatchQueue.main.async(execute: {
            PHPhotoLibrary.shared().performChanges({
                PHAssetCollectionChangeRequest.creationRequestForAssetCollection(withTitle: CustomPhotoAlbum.albumName)   // create an asset collection with the album name
            }) { success, error in
                if success {
                    self.assetCollection = self.fetchAssetCollectionForAlbum()
                } else {
                    print("error \(error)")
                }
            }
        })
    }
    
    func fetchAssetCollectionForAlbum() -> PHAssetCollection! {
        let fetchOptions = PHFetchOptions()
        fetchOptions.predicate = NSPredicate(format: "title = %@", CustomPhotoAlbum.albumName)
        let collection = PHAssetCollection.fetchAssetCollections(with: .album, subtype: .any, options: fetchOptions)
        
        if let _: AnyObject = collection.firstObject {
            return collection.firstObject! as PHAssetCollection
        }
        return nil
    }
    
    func saveImage(image: UIImage) {
        if assetCollection == nil {
            return                          // if there was an error upstream, skip the save
        }
        PHPhotoLibrary.shared().performChanges({
            let assetChangeRequest = PHAssetChangeRequest.creationRequestForAsset(from: image)
            let assetPlaceHolder = assetChangeRequest.placeholderForCreatedAsset
            let albumChangeRequest = PHAssetCollectionChangeRequest(for: self.assetCollection)
            albumChangeRequest!.addAssets([assetPlaceHolder] as NSFastEnumeration)
        }, completionHandler: nil)
        //assetCollection = nil
    }
}
