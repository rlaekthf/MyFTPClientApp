import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

public class FTPClientApp {

    private JFrame frame;
    private JTextField serverField;
    private JTextField portField;
    private JTextField userField;
    private JPasswordField passwordField;
    private JTextArea logArea;
   // private MyFtpClient ftpClient;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            new FTPClientApp();
        });
    }

    public FTPClientApp() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("FTP Client");
        frame.setSize(1200, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel serverLabel = new JLabel("Server:");
        serverField = new JTextField();
        serverField.setText("xxxxx");

        JLabel portLabel = new JLabel("Port:");
        portField = new JTextField();
        portField.setText("21");

        JLabel userLabel = new JLabel("User:");
        userField = new JTextField();
        userField.setText("xxxxx");

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        passwordField.setText("xxxxx");

        JButton connectButton = new JButton("Connect");
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
            }
        });

        JButton changeDirButton = new JButton("Change Directory");
        changeDirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeDirectory();
            }
        });

        JButton listFilesButton = new JButton("List Files");
        listFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listFiles();
            }
        });

        JButton downloadButton = new JButton("Download");
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downloadFile();
            }
        });

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setLineWrap(true);

        // 드래그 앤 드롭 기능 추가
        logArea.setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
            }

            @Override
            public boolean importData(TransferHandler.TransferSupport support) {
                if (!canImport(support)) {
                    return false;
                }

                Transferable transferable = support.getTransferable();
                try {
                    List<File> files = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                    for (File file : files) {
                        uploadFile(file);
                    }
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        panel.add(serverLabel);
        panel.add(serverField);
        panel.add(portLabel);
        panel.add(portField);
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(connectButton);
        panel.add(changeDirButton);
        panel.add(listFilesButton);
        panel.add(downloadButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(logArea), BorderLayout.CENTER);

        // 드래그 앤 드롭 이벤트 처리
        logArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem clearMenuItem = new JMenuItem("Clear Log");
                    clearMenuItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            logArea.setText("");
                        }
                    });
                    popupMenu.add(clearMenuItem);
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        frame.setVisible(true);
    }

    private void connect() {
        String server = serverField.getText();
        int port = Integer.parseInt(portField.getText());
        String user = userField.getText();
        String password = new String(passwordField.getPassword());
/*
        ftpClient = new MyFtpClient(server, port, user, password);
        if (ftpClient.connect()) {
            log("Connected to FTP server.");
        } else {
            log("Connection failed.");
        }*/
    }

    private void changeDirectory() {
        String path = JOptionPane.showInputDialog(frame, "Enter directory path:");
        if (path != null) {
           // ftpClient.cd(path);
            log("Changed directory to: " + path);
        }
    }

    private void listFiles() {
       /* FTPFile[] files = ftpClient.list();
        if (files != null && files.length > 0) {
            log("Files in the current directory:");
            for (FTPFile file : files) {
                log(file.getName());
            }
        } else {
            log("No files in the current directory.");
        }*/
    }

    private void downloadFile() {
        String fileName = JOptionPane.showInputDialog(frame, "Enter file name to download:");
        if (fileName != null) {
           /* File file = ftpClient.get(fileName, fileName);
            if (file != null) {
                log("Downloaded file: " + file.getAbsolutePath());
            } else {
                log("Failed to download file: " + fileName);
            }*/
        }
    }

    private void uploadFile(File file) {
    	/*
        if (ftpClient.upload(file)) {
            log("Uploaded file: " + file.getAbsolutePath());
        } else {
            log("Failed to upload file: " + file.getAbsolutePath());
        }*/
    }

    private void log(String message) {
        logArea.append(message + "\\n");
    }
}
