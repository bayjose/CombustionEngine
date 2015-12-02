/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

/**
 *
 * @author Bayjose
 */
public enum EnumFileType {
    Script(".txt", "Core/gui/folder/Text.png", new DummyApplication()),
    PortableNetworkGraphic(".png", "Core/gui/folder/Image.png", new DummyApplication()),
    FileNotFound("", "Core/error.png", new DummyApplication());
    
    protected String extension;
    protected String image;
    protected IApplication app;
    
    EnumFileType(String extension, String image, IApplication app){
        this.extension = extension;
        this.image = image;
        this.app = app;
    }
    
    public static EnumFileType getFile(String extension){
        for(int i=0; i<EnumFileType.values().length; i++){
            System.out.println("is:"+extension+" = "+EnumFileType.values()[i].extension);
            if(extension.contains(EnumFileType.values()[i].extension)){
               return EnumFileType.values()[i];
            }
        }
        return EnumFileType.FileNotFound;
    }
}
