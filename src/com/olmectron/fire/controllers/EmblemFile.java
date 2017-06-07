/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.fire.controllers;

import com.olmectron.fire.R;
import com.olmectron.fire.compression.DataView;
import com.olmectron.fire.compression.Huffman;
import com.olmectron.fire.compression.Uint8Array;
import com.olmectron.fire.hex.HexConverts;
import com.olmectron.fire.hex.Hex;
import com.olmectron.material.MaterialDesign;
import com.olmectron.material.components.MaterialToast;
import com.olmectron.material.files.TextFile;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.CRC32;
import java.util.zip.Checksum;
import javafx.stage.FileChooser;

/**
 *
 * @author Edgar
 */
public class EmblemFile {

    public static void releaseFile() {
        lastOpenedFile=null;
        EmblemHex.setFates(false);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private File file;
    private byte[] allBytes;
    private byte[] decompressedBytes;
    private byte[] decompressedEditedBytes;
    public final static String JAP="JAP";
    public final static String WEST="WEST";
    private String region=null;
    public String getRegion(){
        return this.region;
    }
    public byte[] getEditableBytes(){
        return decompressedEditedBytes;
    }
    private String fullDecompressedHex=null;
    public void initData(){
        
        try{
            if(isUncompressed()){
                this.decompressedBytes=Files.readAllBytes(file.toPath());
              this.allBytes=compressBytes(decompressedBytes);
            
            
            }
            else{
              this.allBytes=Files.readAllBytes(file.toPath());
            this.decompressedBytes=decompressBytes(allBytes);
            }
            decompressedEditedBytes=new byte[decompressedBytes.length];
            for(int i=0;i<decompressedBytes.length;i++){
                decompressedEditedBytes[i]=decompressedBytes[i];
            }
            
            getFullDecompressedHex();
        } catch (IOException ex) {
            Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private boolean decompressed=false;
    public boolean isUncompressed(){
        return decompressed;
    }
    private EmblemFile(File file, boolean decompressed){
        this.decompressed=decompressed;
        if(decompressed){
            setRegion(WEST);
            this.file=file;
        }
        
    }
    private EmblemFile(File file, String region){
        
            setRegion(region);
            this.file=file;
          
        
    }
    public String getFullDecompressedHex(){
        if(fullDecompressedHex==null){
            fullDecompressedHex=HexConverts.getHexString(decompressedBytes);
        }
        return fullDecompressedHex;
    }
    private byte[] getHeader(byte[] decmp, int length)
        {
            byte[] Header = new byte[0x10];
            
            System.arraycopy(HexConverts.hexStringToByteArray(Hex.PMOC),0, Header,0, 0x4);
            
            System.arraycopy(DataView.getBytes(2),0, Header,0x4, 0x4);
            System.arraycopy(DataView.getBytes(length),0, Header,0x8, 0x4);
            byte[] checkSumBytes=DataView.getBytes(getChecksumJava(decmp));
            System.arraycopy(checkSumBytes,0, Header,0xC, 0x4);
            //for(int i=0;i<checkSumBytes.length;i++){
            //    System.out.println("checksum "+checkSumBytes[i]);
            //}
           // CRC32 of Decompressed Data.
            return Header;
        }
    private int getChecksumJava(byte[] data){
         Checksum checksum = new CRC32();
               
                /*
                 * To compute the CRC32 checksum for byte array, use
                 *
                 * void update(bytes[] b, int start, int length)
                 * method of CRC32 class.
                 */
                 
                checksum.update(data,0,data.length);
               
                /*
                 * Get the generated checksum using
                 * getValue method of CRC32 class.
                 */
                long lngChecksum = checksum.getValue();
                return (int)lngChecksum;
    }
    private int getChecksum(byte[] dat) // It's just CRC32.
        {
        
            int checksum = Math.abs(0xFFFFFFFF);
            int[] data=Huffman.byteToInt(dat);
            try{
               
            int length = (int)data.length;
            
            
            int[] table = { 0x00000000, 0x77073096, 0xee0e612c, 0x990951ba, 0x076dc419, 0x706af48f, 0xe963a535, 0x9e6495a3, 0x0edb8832, 0x79dcb8a4, 0xe0d5e91e, 0x97d2d988, 0x09b64c2b, 0x7eb17cbd, 0xe7b82d07, 0x90bf1d91, 0x1db71064, 0x6ab020f2, 0xf3b97148, 0x84be41de, 0x1adad47d, 0x6ddde4eb, 0xf4d4b551, 0x83d385c7, 0x136c9856, 0x646ba8c0, 0xfd62f97a, 0x8a65c9ec, 0x14015c4f, 0x63066cd9, 0xfa0f3d63, 0x8d080df5, 0x3b6e20c8, 0x4c69105e, 0xd56041e4, 0xa2677172, 0x3c03e4d1, 0x4b04d447, 0xd20d85fd, 0xa50ab56b, 0x35b5a8fa, 0x42b2986c, 0xdbbbc9d6, 0xacbcf940, 0x32d86ce3, 0x45df5c75, 0xdcd60dcf, 0xabd13d59, 0x26d930ac, 0x51de003a, 0xc8d75180, 0xbfd06116, 0x21b4f4b5, 0x56b3c423, 0xcfba9599, 0xb8bda50f, 0x2802b89e, 0x5f058808, 0xc60cd9b2, 0xb10be924, 0x2f6f7c87, 0x58684c11, 0xc1611dab, 0xb6662d3d, 0x76dc4190, 0x01db7106, 0x98d220bc, 0xefd5102a, 0x71b18589, 0x06b6b51f, 0x9fbfe4a5, 0xe8b8d433, 0x7807c9a2, 0x0f00f934, 0x9609a88e, 0xe10e9818, 0x7f6a0dbb, 0x086d3d2d, 0x91646c97, 0xe6635c01, 0x6b6b51f4, 0x1c6c6162, 0x856530d8, 0xf262004e, 0x6c0695ed, 0x1b01a57b, 0x8208f4c1, 0xf50fc457, 0x65b0d9c6, 0x12b7e950, 0x8bbeb8ea, 0xfcb9887c, 0x62dd1ddf, 0x15da2d49, 0x8cd37cf3, 0xfbd44c65, 0x4db26158, 0x3ab551ce, 0xa3bc0074, 0xd4bb30e2, 0x4adfa541, 0x3dd895d7, 0xa4d1c46d, 0xd3d6f4fb, 0x4369e96a, 0x346ed9fc, 0xad678846, 0xda60b8d0, 0x44042d73, 0x33031de5, 0xaa0a4c5f, 0xdd0d7cc9, 0x5005713c, 0x270241aa, 0xbe0b1010, 0xc90c2086, 0x5768b525, 0x206f85b3, 0xb966d409, 0xce61e49f, 0x5edef90e, 0x29d9c998, 0xb0d09822, 0xc7d7a8b4, 0x59b33d17, 0x2eb40d81, 0xb7bd5c3b, 0xc0ba6cad, 0xedb88320, 0x9abfb3b6, 0x03b6e20c, 0x74b1d29a, 0xead54739, 0x9dd277af, 0x04db2615, 0x73dc1683, 0xe3630b12, 0x94643b84, 0x0d6d6a3e, 0x7a6a5aa8, 0xe40ecf0b, 0x9309ff9d, 0x0a00ae27, 0x7d079eb1, 0xf00f9344, 0x8708a3d2, 0x1e01f268, 0x6906c2fe, 0xf762575d, 0x806567cb, 0x196c3671, 0x6e6b06e7, 0xfed41b76, 0x89d32be0, 0x10da7a5a, 0x67dd4acc, 0xf9b9df6f, 0x8ebeeff9, 0x17b7be43, 0x60b08ed5, 0xd6d6a3e8, 0xa1d1937e, 0x38d8c2c4, 0x4fdff252, 0xd1bb67f1, 0xa6bc5767, 0x3fb506dd, 0x48b2364b, 0xd80d2bda, 0xaf0a1b4c, 0x36034af6, 0x41047a60, 0xdf60efc3, 0xa867df55, 0x316e8eef, 0x4669be79, 0xcb61b38c, 0xbc66831a, 0x256fd2a0, 0x5268e236, 0xcc0c7795, 0xbb0b4703, 0x220216b9, 0x5505262f, 0xc5ba3bbe, 0xb2bd0b28, 0x2bb45a92, 0x5cb36a04, 0xc2d7ffa7, 0xb5d0cf31, 0x2cd99e8b, 0x5bdeae1d, 0x9b64c2b0, 0xec63f226, 0x756aa39c, 0x026d930a, 0x9c0906a9, 0xeb0e363f, 0x72076785, 0x05005713, 0x95bf4a82, 0xe2b87a14, 0x7bb12bae, 0x0cb61b38, 0x92d28e9b, 0xe5d5be0d, 0x7cdcefb7, 0x0bdbdf21, 0x86d3d2d4, 0xf1d4e242, 0x68ddb3f8, 0x1fda836e, 0x81be16cd, 0xf6b9265b, 0x6fb077e1, 0x18b74777, 0x88085ae6, 0xff0f6a70, 0x66063bca, 0x11010b5c, 0x8f659eff, 0xf862ae69, 0x616bffd3, 0x166ccf45, 0xa00ae278, 0xd70dd2ee, 0x4e048354, 0x3903b3c2, 0xa7672661, 0xd06016f7, 0x4969474d, 0x3e6e77db, 0xaed16a4a, 0xd9d65adc, 0x40df0b66, 0x37d83bf0, 0xa9bcae53, 0xdebb9ec5, 0x47b2cf7f, 0x30b5ffe9, 0xbdbdf21c, 0xcabac28a, 0x53b39330, 0x24b4a3a6, 0xbad03605, 0xcdd70693, 0x54de5729, 0x23d967bf, 0xb3667a2e, 0xc4614ab8, 0x5d681b02, 0x2a6f2b94, 0xb40bbe37, 0xc30c8ea1, 0x5a05df1b, 0x2d02ef8d };
            if (length > 0)
            {
                int ofs = -1;
                if (length % 2 == 1)
                {
                    ofs++;
                    checksum = table[(data[ofs] ^ (checksum & 0xFF))] ^ (checksum >> 8);
                }
                int v12;
                for (int i = length >> 1; i > 0; checksum = table[(data[ofs] ^ (v12 & 0xFF))] ^ (v12 >> 8))
                {
                    i--;
                    int v10 = (data[ofs + 1] ^ (checksum & 0xFF));
                    ofs += 2;
                    v12 = table[v10] ^ (checksum >> 8);
                }
            }
            return ~checksum;}
           catch(ArrayIndexOutOfBoundsException ex){
               return ~checksum;
           }
        }
    public void saveCompressedEditedFile(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        byte[] compressedBytes=null;
        try {
            byte[] start=Arrays.copyOf(decompressedEditedBytes, 0xC0);
            byte[] header=getHeader(decompressedEditedBytes, (int)(decompressedEditedBytes.length - 0xC0));
            byte[] compressed=new Huffman().compressArray(Arrays.copyOfRange(decompressedEditedBytes,0xC0,decompressedEditedBytes.length));
            outputStream.write( start);
            outputStream.write( header );
            outputStream.write( compressed );
            compressedBytes=outputStream.toByteArray();
            
        } catch (IOException ex) {
            Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                outputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        FileChooser chooser=new  FileChooser();
        chooser.setTitle(R.string.export_decompressed_file.get());
        chooser.setInitialDirectory(file.getParentFile());
        chooser.setInitialFileName("compressed_"+file.getName());
        
        File savedFile=null;
        try{
            savedFile=chooser.showSaveDialog(MaterialDesign.primary);
        }
        catch(IllegalArgumentException ex){
            System.err.println(ex.getMessage());
            chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            
            savedFile=chooser.showSaveDialog(MaterialDesign.primary);
        }
        if(savedFile!=null){
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(savedFile.getAbsolutePath());
                fos.write(compressedBytes);
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fos.close();
                    new MaterialToast(R.string.decompressed_file_exported.get()).unhide();
                } catch (IOException ex) {
                    Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else{
            System.out.println("You forgot to save");
        }
    }
    public File getFile(){
        return file;
    }
    private void processEditedUnits(){
      ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
      ByteArrayOutputStream unitsStream=new ByteArrayOutputStream();
      try{
          int intStart=(getFullDecompressedHex().indexOf(Hex.CHARACTERS_HEADER)+Hex.CHARACTERS_HEADER.length()+6)/2;
          int intEnd=getFullDecompressedHex().indexOf(Hex.CHARACTERS_FOOTER)/2;
          
        byte[] start=Arrays.copyOf(decompressedEditedBytes,intStart);
        
        for(int i=0;i<R.getUnitList().size();i++){
            if(R.getUnitList().get(i).isAlive()){
                unitsStream.write(R.getUnitList().get(i).getBytesBlock());
            }
        }
        for(int i=0;i<R.getUnitList().size();i++){
            if(!R.getUnitList().get(i).isAlive()){
                unitsStream.write(R.getUnitList().get(i).getBytesBlock());                
            }
        }
        byte[] end=Arrays.copyOfRange(decompressedEditedBytes,intEnd,decompressedEditedBytes.length);
        outputStream.write(start);
        outputStream.write(unitsStream.toByteArray());
        outputStream.write(end);
        decompressedEditedBytes=outputStream.toByteArray();
      }
      catch (IOException ex) {
            Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                unitsStream.close();
                outputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void processEditedBytes(){
        processEditedUnits();
    }
    public void overwriteActualFile(){
       processEditedBytes();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        byte[] compressedBytes=null;
        try {
            byte[] start=Arrays.copyOf(decompressedEditedBytes, 0xC0);
            byte[] header=getHeader(decompressedEditedBytes, (int)(decompressedEditedBytes.length - 0xC0));
            byte[] compressed=new Huffman().compressArray(Arrays.copyOfRange(decompressedEditedBytes,0xC0,decompressedEditedBytes.length));
            outputStream.write( start);
            outputStream.write( header );
            outputStream.write( compressed );
            compressedBytes=outputStream.toByteArray();
            
        } catch (IOException ex) {
            Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                outputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
      
        File savedFile=null;
      try{ 
            savedFile=getFile();
            if(savedFile.exists()){
                savedFile.delete();
                savedFile.createNewFile();
            }}
      catch(IOException ex){
          System.err.println(ex.getMessage());
      }
        if(savedFile!=null){
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(savedFile.getAbsolutePath());
                fos.write(compressedBytes);
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fos.close();
                    new MaterialToast(R.string.overwrite_success.get()).unhide();
                } catch (IOException ex) {
                    Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else{
            System.out.println("You forgot to save");
        }
    }
    public void saveCompressedOriginalFile(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        byte[] compressedBytes=null;
        try {
            byte[] start=Arrays.copyOf(decompressedBytes, 0xC0);
            byte[] header=getHeader(decompressedBytes, (int)(decompressedBytes.length - 0xC0));
            byte[] compressed=new Huffman().compressArray(Arrays.copyOfRange(decompressedBytes,0xC0,decompressedBytes.length));
            outputStream.write( start);
            outputStream.write( header );
            outputStream.write( compressed );
            compressedBytes=outputStream.toByteArray();
            
        } catch (IOException ex) {
            Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                outputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        FileChooser chooser=new  FileChooser();
        chooser.setTitle(R.string.export_decompressed_file.get());
        chooser.setInitialDirectory(file.getParentFile());
        chooser.setInitialFileName("compressed_"+file.getName());
        
        File savedFile=null;
        try{
            savedFile=chooser.showSaveDialog(MaterialDesign.primary);
        }
        catch(IllegalArgumentException ex){
            System.err.println(ex.getMessage());
            chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            
            savedFile=chooser.showSaveDialog(MaterialDesign.primary);
        }
        if(savedFile!=null){
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(savedFile.getAbsolutePath());
                fos.write(compressedBytes);
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fos.close();
                    new MaterialToast(R.string.decompressed_file_exported.get()).unhide();
                } catch (IOException ex) {
                    Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else{
            System.out.println("You forgot to save");
        }
    }
    public void saveDecompressedEditedFile(){
        FileChooser chooser=new  FileChooser();
        chooser.setTitle(R.string.export_decompressed_file.get());
        chooser.setInitialDirectory(file.getParentFile());
        chooser.setInitialFileName("decompressed_"+file.getName());
        
        File savedFile=null;
        try{
            savedFile=chooser.showSaveDialog(MaterialDesign.primary);
        }
        catch(IllegalArgumentException ex){
            System.err.println(ex.getMessage());
            chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            
            savedFile=chooser.showSaveDialog(MaterialDesign.primary);
        }
        if(savedFile!=null){
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(savedFile.getAbsolutePath());
                fos.write(decompressedEditedBytes);
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fos.close();
                    new MaterialToast(R.string.decompressed_file_exported.get()).unhide();
                } catch (IOException ex) {
                    Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else{
            System.out.println("You forgot to save");
        }
    }
    public void saveDecompressedOriginalFile(){
        FileChooser chooser=new  FileChooser();
        chooser.setTitle(R.string.export_decompressed_file.get());
        chooser.setInitialDirectory(file.getParentFile());
        chooser.setInitialFileName("decompressed_"+file.getName());
        
        File savedFile=null;
        try{
            savedFile=chooser.showSaveDialog(MaterialDesign.primary);
        }
        catch(IllegalArgumentException ex){
            System.err.println(ex.getMessage());
            chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            
            savedFile=chooser.showSaveDialog(MaterialDesign.primary);
        }
        if(savedFile!=null){
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(savedFile.getAbsolutePath());
                fos.write(decompressedBytes);
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fos.close();
                    new MaterialToast(R.string.decompressed_file_exported.get()).unhide();
                } catch (IOException ex) {
                    Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else{
            System.out.println("You forgot to save");
        }
    }
    private byte[] compressBytes(byte[] all){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        byte[] compressedBytes=null;
        try {
            byte[] start=Arrays.copyOf(all, 0xC0);
            byte[] header=getHeader(all, (int)(all.length - 0xC0));
            byte[] compressed=new Huffman().compressArray(Arrays.copyOfRange(all,0xC0,all.length));
            outputStream.write( start);
            outputStream.write( header );
            outputStream.write( compressed );
            compressedBytes=outputStream.toByteArray();
            
        } catch (IOException ex) {
            Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                outputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return compressedBytes;
    }
    private byte[] decompressBytes(byte[] all){
        int decompressStart=0;
        switch(getRegion()){
            case JAP:
                decompressStart=0x80;
                break;
            case WEST:
                decompressStart=0xC0;
                break;
                
        }
            ByteArrayOutputStream out=null;
            byte[] data=null;
        try {
            byte[] header=Arrays.copyOf(all, decompressStart);
            Uint8Array decompressedArray=Huffman.decompressArray(Arrays.copyOfRange(all, decompressStart+0x10, all.length));
            
            out=new ByteArrayOutputStream();
            out.write(header);
            //ByteArrayOutputStream ddd=new ByteArrayOutputStream();
            for(long i=0;i<decompressedArray.length();i++){
                out.write((int)decompressedArray.get(i));
              //  ddd.write((int)decompressedArray.get(i));
            }
            data=out.toByteArray();
            //System.err.println(HexConverts.getHexString(ddd.toByteArray()));
        } catch (IOException ex) {
            Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        return data;
        
    }
    private void setRegion(String region){
        this.region=region;
    }
    private static EmblemFile lastOpenedFile;
    /*public static EmblemFile getLastOpenedFile(){
        return lastOpenedFile;
    }*/
    public static EmblemFile getActualFile(){
        return lastOpenedFile;
    }
    public static EmblemFile openFile(){
        return openFile(null);
    }
    public static EmblemFile openFile(File archivo){
        File opened=null;
        if(archivo==null){
        FileChooser chooser=new FileChooser();
        chooser.setTitle(R.string.open_file.get());
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        opened=chooser.showOpenDialog(MaterialDesign.primary);
        }
        else{
            opened=archivo;
        }
        
        if(opened!=null){
            try {
                return checkChapterFile(opened);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                return null;
                //Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    private static boolean isDecompressed(File file){
        
        try {
            
            if(Files.size(file.toPath())<1048576){
                TextFile texto=new TextFile(file.getAbsolutePath());
                String text=texto.getText();
                if(text.contains("TINU") && text.contains("IFER") && text.contains("EDNI") &&
                        text.contains("RESU")){
                    return true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static EmblemFile checkChapterFile(File file) throws IOException{
        RandomAccessFile randomAccess = null;
        EmblemFile success=null;
        try {
            randomAccess=new RandomAccessFile(file,"r");
            randomAccess.seek(0x80);
            byte[] japaneseChecksumArray=new byte[4];
            
            randomAccess.read(japaneseChecksumArray, 0, 4);
            
            randomAccess.seek(0xC0);
            byte[] checksumArray=new byte[4];
            randomAccess.read(checksumArray, 0, 4);
            //byte[] fileArray= Files.readAllBytes(file.toPath());
            //byte[] checksumArray=Arrays.copyOfRange(fileArray,0xC0,0xD0);
            String checksumString=HexConverts.getHexString(checksumArray);
            String japaneseChecksumString=HexConverts.getHexString(japaneseChecksumArray);
            
            if(checksumString.equals(Hex.PMOC) || japaneseChecksumString.equals(Hex.PMOC)){
               System.out.println("File loaded successfully");
                if(checksumString.equals(Hex.PMOC)){
                    success=new EmblemFile(file,WEST);
                   
                }
                else if(japaneseChecksumString.equals(Hex.PMOC)){
                     success=new EmblemFile(file,JAP);
                   
                }
            lastOpenedFile=success;
               
            }
            else if(isDecompressed(file)){
                success=new EmblemFile(file,true);
                lastOpenedFile=success;
            }
            else{
                new MaterialToast(R.string.invalid_file.get(),MaterialToast.LENGTH_LONG).unhide();
            }

           
            
                 
        } 
        catch(ArrayIndexOutOfBoundsException ex){
            
                new MaterialToast(R.string.invalid_file.get(),MaterialToast.LENGTH_LONG).unhide();
        }
        catch(NullPointerException ex){
            Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            Logger.getLogger(EmblemFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            randomAccess.close();
            
        }
        return success;
    }
}
