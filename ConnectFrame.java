	import java.awt.Graphics;
	import java.awt.Image;
	import java.awt.Insets;
	import java.awt.Rectangle;
	import java.awt.Toolkit;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.image.BufferedImage;
	import java.io.File;
	import javax.imageio.ImageIO;
	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JCheckBox;
	import javax.swing.JFileChooser;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JSeparator;
	import javax.swing.JTextField;
	import javax.swing.SwingConstants;
	import javax.swing.filechooser.FileFilter;
	import javax.swing.filechooser.FileNameExtensionFilter;

	
	@SuppressWarnings("serial")
	public class ConnectFrame extends JFrame implements ActionListener {
		private JTextField txt_address;
		private JTextField txt_nickname;
		private JTextField txt_port;
		private JLabel lbl_image;
		private JButton btn_image;
		private JButton btn_connect;
		private JCheckBox chk_spectate;
	
		public static void main(String[] args){
			new ConnectFrame();
		}
		
		public ConnectFrame(){
			setAlwaysOnTop(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(false);
			setType(Type.POPUP);
			setIconImage(Toolkit.getDefaultToolkit().getImage(ConnectFrame.class.getResource("/images/icon.png")));
			setTitle("  PongGame - Launcher");
			setBounds(new Rectangle(0, 0, 352, 148));
			getContentPane().setLayout(null);
			setLocationRelativeTo(null);
			
			lbl_image = new JLabel("");
			lbl_image.setIcon(new ImageIcon(ConnectFrame.class.getResource("/images/avatar.png")));
			lbl_image.setBounds(10, 11, 64, 64);
			getContentPane().add(lbl_image);
			
			btn_image = new JButton("Change");
			btn_image.setMargin(new Insets(2, 2, 2, 2));
			btn_image.setBounds(10, 86, 64, 23);
			btn_image.addActionListener(this);
			getContentPane().add(btn_image);
			
			btn_connect = new JButton("Connect");
			btn_connect.setBounds(95, 73, 243, 23);
			btn_connect.addActionListener(this);
			getContentPane().add(btn_connect);
			
			txt_address = new JTextField();
			new TextPrompt("IP-Adress", txt_address);
			txt_address.setHorizontalAlignment(SwingConstants.CENTER);
			txt_address.setBounds(95, 11, 159, 20);
			getContentPane().add(txt_address);
			txt_address.setColumns(10);		
			
			txt_port = new JTextField();
			new TextPrompt("Port", txt_port);
			txt_port.setHorizontalAlignment(SwingConstants.CENTER);
			txt_port.setColumns(10);
			txt_port.setBounds(264, 11, 74, 20);
			getContentPane().add(txt_port);
			
			txt_nickname = new JTextField();
			new TextPrompt("Nickname", txt_nickname);
			txt_nickname.setHorizontalAlignment(SwingConstants.CENTER);
			txt_nickname.setColumns(10);
			txt_nickname.setBounds(95, 42, 159, 20);
			getContentPane().add(txt_nickname);
			
			chk_spectate = new JCheckBox("Spectate?");
			chk_spectate.setMargin(new Insets(2, 2, 2, 0));
			chk_spectate.setBounds(256, 41, 82, 23);
			getContentPane().add(chk_spectate);
			
			JSeparator sep = new JSeparator();
			sep.setOrientation(SwingConstants.VERTICAL);
			sep.setBounds(84, 0, 2, 121);
			getContentPane().add(sep);
			
			JLabel lbl_credits = new JLabel("v 1.0 - made by: Julius, Nico, ...");
			lbl_credits.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_credits.setBounds(95, 98, 243, 23);
			getContentPane().add(lbl_credits);
						
			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btn_image)){
				changeImage();
			}else if(e.getSource().equals(btn_connect)){
				startGame();
			}
		}

		private void startGame() {
			if(txt_address.getText() != null && txt_port.getText() != null && txt_nickname != null){
				Image image = ((ImageIcon)lbl_image.getIcon()).getImage();
				BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), 1);
			    Graphics gc = bufferedImage.createGraphics();
			    gc.drawImage(image, 0, 0, null);
				
				System.out.println("Adress: " + txt_address.getText() + ":" + txt_port.getText());
				System.out.println("Nickname: " + txt_nickname.getText());
				System.out.println("Spectate: " + chk_spectate.isSelected());

			}
		}

		private void changeImage() {
		    JFileChooser filechooser= new JFileChooser();
		    FileFilter imageFilter = new FileNameExtensionFilter("Image Files", ImageIO.getReaderFileSuffixes());
		    filechooser.setFileFilter(imageFilter);
		    filechooser.setDialogTitle("Choose your new Avatar");
		    filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		    int result = filechooser.showOpenDialog(this);
		    if(result == JFileChooser.APPROVE_OPTION) {
		        File file = filechooser.getSelectedFile();
		        BufferedImage bi;
		        try {
		            bi = ImageIO.read(file);
		            if(bi.getHeight() == 64 && bi.getWidth() == 64){
		            	lbl_image.setIcon(new ImageIcon(bi));
		            }
		        }
		        catch(Exception e) { }
		        lbl_image.repaint();
		    }
		}
		
	}
