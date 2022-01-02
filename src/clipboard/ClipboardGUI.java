package clipboard;


// IMPORTS
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import java.awt.Image;
import java.util.Optional;

// the GUI was created using the IntelliJ designer
public class ClipboardGUI extends JFrame implements ActionListener,ClipboardListener.clipShare {
    private JPanel mainPanel;
    private JButton txtBtn;
    private JButton imgBtn;
    private JButton exitButton;
    private JButton clrBtn;
    private JScrollPane scrollList;
    private JList contentList;
    private JButton copyBtn;
    private final DefaultListModel<String> txtModel = new DefaultListModel<>();
    private final DefaultListModel<Object> imgModel = new DefaultListModel<>();

    private Boolean current = true;

    private ClipboardListener clipboardL = new ClipboardListener();

    private int amount;


    public ClipboardGUI(Optional<String> amount) {

        // setting the maximum amount of items (seperate for images and text)
        if (amount.isPresent()) {
            this.amount = Integer.parseInt(amount.get());
        } else {
            this.amount = 100;
        }

        txtModel.addElement("Here the contents of the clipboard will be added as they're copied......");
        contentList.setModel(txtModel);


        setContentPane(mainPanel);
        setTitle("Clipboard Application");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        // adding action listeners
        exitButton.addActionListener(this);
        clrBtn.addActionListener(this);
        imgBtn.addActionListener(this);
        txtBtn.addActionListener(this);
        copyBtn.addActionListener(this);


        clipboardL.run(this);
        clipboardL.setType(current);


    }

    // LISTENERS

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (exitButton.equals(source)) {
            System.exit(0);
        } else if (clrBtn.equals(source)) {
            txtModel.clear();
            imgModel.clear();
        } else if (txtBtn.equals(source)) {
            current = true;
            clipboardL.setType(true);
            contentList.setModel(txtModel);
        } else if (imgBtn.equals(source)) {
            current = false;
            clipboardL.setType(false);
            contentList.setModel(imgModel);
        } else if (copyBtn.equals(source)) {
            clipboardL.setClipboard(contentList.getSelectedValue(), current);


        }


    }

    // SHARED INTERFACE FUNCTIONS

    public void processStringData(String data) {
        txtModel.addElement(data);
        if (txtModel.capacity() > amount) {
            txtModel.remove(0);
        }
    }

    public void processImageData(Object data) {
        Image img = (Image) data;
        ImageIcon img2 = new ImageIcon(img);
        imgModel.addElement(img2);
        if (imgModel.capacity() > amount) {
            imgModel.remove(0);
        }
    }


    // MAIN FUNCTION

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ClipboardGUI clipboardGUI = new ClipboardGUI(Optional.of(args[0]));

            }


        });
    }
}



