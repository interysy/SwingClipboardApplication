package clipboard;



import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;


public class ClipboardListener extends Thread implements ClipboardOwner {

    protected final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    private clipShare GUI;
    private Boolean type = true;


    // SHARING DATA WITH GUI
    interface clipShare {

        void processImageData(Object data);
        void processStringData(String data);
    }

    // DATA PROCESSING


    public void processStringContent()  {
        try {
            GUI.processStringData((String) clipboard.getData(DataFlavor.stringFlavor));
        } catch (UnsupportedFlavorException e1) {
        } catch (IOException e2) {
            System.out.println(e2);
        }
    }

    public void processImageContent() {
        try {
            GUI.processImageData(clipboard.getContents(this).getTransferData(DataFlavor.imageFlavor));
        } catch (UnsupportedFlavorException e1) {
        } catch (IOException e2) {
            System.out.println(e2);
        }
    }


    // OWNERSHIP RELATED


    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        try {
            this.sleep(250);  //wait for a little while to allow data to enter clipboard
        } catch(Exception e) {
            System.out.println("Exception: " + e);
        }
        getOwnership();

    }

    public void getOwnership() {
        clipboard.setContents(clipboard.getContents(this),this);

        processStringContent();
        processImageContent();

    }


    // SETTERS


    public void setClipboard(Object chosen,Boolean type) {
        if (chosen != null) {
            if (type) {
                clipboard.setContents(new StringSelection((String) chosen), this);
            } else {
                ImageIcon imageAsIcon = (ImageIcon) chosen;
                Image imageAsImage = imageAsIcon.getImage();
                ImageForTransfer img = new ImageForTransfer(imageAsImage);
                clipboard.setContents(img, this);
            }
            System.out.println("The clipboard has been set");
        } else {
            System.out.println("There is not data to copy");
        }
    }

    public void setType(Boolean type) {
        this.type = type;

    }


    // THREAD


    public void run(clipShare GUI) {
        this.GUI = GUI;
        getOwnership();



        }

        // Custom class to create a transferable image - allows copying to clipboard from GUI
    // not all methods necessary in design, but are abstract so have to be filled in
    private class ImageForTransfer implements Transferable {

        private Image image;
        private DataFlavor[] dataFlavor = new DataFlavor[1];

        private ImageForTransfer(Image image) {
            this.image = image;
            dataFlavor[0] = DataFlavor.imageFlavor;
        }


        @Override
        public DataFlavor[] getTransferDataFlavors() {

            return dataFlavor;
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            if (flavor.equals(dataFlavor[0])) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
            if (flavor.equals(DataFlavor.imageFlavor) && image != null) {
                return image;
            } else {
                throw new UnsupportedFlavorException(flavor);
            }
        }
    }


    }






