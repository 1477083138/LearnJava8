package xyz.haocode.logger;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.*;

/**
 *
 * @author LiuHao
 * @date 2020/4/15 10:56
 * @description 日志可视化观察器
*/
public class LoggingImageViewer {
    public static void main(String[] args) {
        if(System.getProperty("java.util.logging.config.class")==null
        && System.getProperty("java.util.logging.config.file") == null){
            try{
                Logger.getLogger("xyz.haocode.logger").setLevel(Level.ALL);
                final int LOG_ROTATION_COUNT = 10;
                Handler handler = new FileHandler("%h/LoggingImageViewer.log",0,LOG_ROTATION_COUNT);
                Logger.getLogger("xyz.haocode.logger").addHandler(handler);
            }catch (IOException e){

                Logger.getLogger("xyz.haocode.logger").log(Level.SEVERE,"Cna't crate log file handler",e);
            }
        }

        EventQueue.invokeLater(()->{
            Handler windowHandler = new WindowHandler();
            windowHandler.setLevel(Level.ALL);
            Logger.getLogger("xyz.haocode.logger").addHandler(windowHandler);

            JFrame frame = new ImageViewerFrame();
            frame.setTitle("LoggingImageViewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Logger.getLogger("xyz.haocode.logger").fine("Showing frame");
            frame.setVisible(true);
        });
    }
}
class ImageViewerFrame extends JFrame{
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;

    private JLabel label;
    private static Logger logger = Logger.getLogger("xyz.haocode.logger");

    public ImageViewerFrame(){
        logger.entering("ImageVirewFrame","<init>");
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new FileOpenListener());

        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(e -> {
            logger.fine("Exiting.");
            System.exit(0);
        });

        label = new JLabel();
        add(label);
        logger.exiting("ImageViewerFrame","<init>");
    }

    private class FileOpenListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            logger.entering("ImageViewerFrame.FileOpenListener","actionPerformed",e);

            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));

            chooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
                }

                @Override
                public String getDescription() {
                    return "GIF IMAGES";
                }
            });

            int r = chooser.showOpenDialog(ImageViewerFrame.this);

            if(r == JFileChooser.APPROVE_OPTION){
                String name = chooser.getSelectedFile().getPath();
                logger.log(Level.FINE,"Reading file {0} ",name);
                label.setIcon(new ImageIcon(name));
            }else {
                logger.fine("File open dialog canceled");
            }
            logger.exiting("ImageViewerFrame.FileOpenListener","actionPerformed");
        }
    }
}

class WindowHandler extends StreamHandler
{
    private JFrame jFrame;

    public WindowHandler(){
        jFrame = new JFrame();

        final JTextArea output = new JTextArea();
        output.setEditable(false);
        jFrame.setSize(200,200);
        jFrame.add(new JScrollPane(output));
        jFrame.setFocusableWindowState(false);
        jFrame.setVisible(true);
        setOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }

            @Override
            public void write(byte[] b, int off, int len){
                output.append(new String(b,off,len));
            }
        });
    }

    @Override
    public void publish(LogRecord record){
        if(!jFrame.isVisible()){
            return;
        }
        super.publish(record);
        flush();
    }
}