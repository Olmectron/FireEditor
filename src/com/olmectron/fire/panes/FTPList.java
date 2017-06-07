/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.fire.panes;

import com.olmectron.material.components.MaterialCard;
import com.olmectron.material.components.MaterialDisplayText;
import com.olmectron.material.components.MaterialFlowList;
import com.olmectron.material.components.MaterialStandardListItem;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Edgar
 */
public class FTPList{} /*extends MaterialFlowList<FTPFile> {
    public FTPList(String ip){
        super(new StackPane());
        connect(ip);
    }
    public void connect(String ip){
        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect(ip, 5000);
            ftpClient.login("anonymous", "");
            
// lists files and directories in the current working directory
FTPFile[] files = ftpClient.listFiles();
 
// iterates over the files and prints details for each
DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
for (FTPFile file : files) {
    this.addItem(file);
    //String details = file.getName();
    //if (file.isDirectory()) {
    //    details = "[" + details + "]";
    //}
    //details += "\t\t" + file.getSize();
    //details += "\t\t" + dateFormater.format(file.getTimestamp().getTime());
    //System.out.println(details);
}       } catch (IOException ex) {
            Logger.getLogger(FTPList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void cardConverter(MaterialCard card, FTPFile item, MaterialStandardListItem<FTPFile> itemContainer) {
        card.setCardWidth(500);
        MaterialDisplayText name=new MaterialDisplayText(item.getName());
        card.addComponent(name);
            //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean asCard() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void onItemClick(FTPFile item, MouseEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node itemConverter(FTPFile item, MaterialStandardListItem<FTPFile> itemContainer) {
        return null;
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
*/