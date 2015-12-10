	import java.awt.BorderLayout;
	import java.awt.Insets;
	import java.awt.Rectangle;
	import java.awt.Toolkit;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.KeyEvent;
	import java.awt.event.KeyListener;
	import java.awt.image.BufferedImage;
	import java.io.ByteArrayOutputStream;
	import java.io.File;
	import java.util.Arrays;
	import java.util.regex.Pattern;
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
	import javax.swing.UIManager;
	import javax.swing.filechooser.FileFilter;
	import javax.swing.filechooser.FileNameExtensionFilter;
	import java.awt.Color;
	import java.awt.Font;
	
	@SuppressWarnings("serial")
	public class ConnectFrame extends JFrame implements ActionListener, KeyListener {
		private Pattern PATTERN = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
		private JCheckBox chk_spectate;
		private JTextField txt_address;
		private JTextField txt_nickname;
		private JTextField txt_port;
		private JButton btn_image;
		private JButton btn_connect;
		private JLabel lbl_image;
		private JLabel lbl_address;
		private JLabel lbl_port;
		private JLabel lbl_nickname;
		private byte[] image;
		
		public static void main(String[] args) {
				new ConnectFrame();
		}
		
		public ConnectFrame() {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception ex) {}
			
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
			lbl_image.setBorder(null);
			lbl_image.setIcon(new ImageIcon(ConnectFrame.class.getResource("/images/avatar.png")));
			lbl_image.setBounds(10, 11, 64, 64);
			getContentPane().add(lbl_image);
			
			btn_image = new JButton("Change");
			btn_image.setMargin(new Insets(2, 2, 2, 2));
			btn_image.setBounds(10, 86, 64, 23);
			btn_image.addActionListener(this);
			getContentPane().add(btn_image);
			
			btn_connect = new JButton("Connect");
			btn_connect.setEnabled(false);
			btn_connect.setBounds(95, 73, 243, 23);
			btn_connect.addActionListener(this);
			getContentPane().add(btn_connect);
			
			txt_address = new JTextField();
			lbl_address = new JLabel("IP-Adresse");
			lbl_address.setFont(new Font("Tahoma", Font.PLAIN, 9));
			lbl_address.setForeground(Color.GRAY);
			lbl_address.setHorizontalAlignment(SwingConstants.CENTER);
			txt_address.setLayout( new BorderLayout() );
			txt_address.add(lbl_address);
			txt_address.setHorizontalAlignment(SwingConstants.CENTER);
			txt_address.setBounds(95, 11, 159, 20);
			txt_address.addKeyListener(this);
			txt_address.setColumns(10);	
			getContentPane().add(txt_address);
			
			txt_port = new JTextField();
			lbl_port = new JLabel("Port");
			lbl_port.setFont(new Font("Tahoma", Font.PLAIN, 9));
			lbl_port.setForeground(Color.GRAY);
			lbl_port.setHorizontalAlignment(SwingConstants.CENTER);
			txt_port.setLayout( new BorderLayout() );
			txt_port.add(lbl_port);
			txt_port.setHorizontalAlignment(SwingConstants.CENTER);
			txt_port.setColumns(10);
			txt_port.setBounds(264, 11, 74, 20);
			txt_port.addKeyListener(this);
			getContentPane().add(txt_port);
			
			txt_nickname = new JTextField();
			lbl_nickname = new JLabel("Nickname (max 20 chars)");
			lbl_nickname.setForeground(Color.GRAY);
			lbl_nickname.setFont(new Font("Tahoma", Font.PLAIN, 9));
			lbl_nickname.setHorizontalAlignment(SwingConstants.CENTER);
			txt_nickname.setLayout( new BorderLayout() );
			txt_nickname.add(lbl_nickname);
			txt_nickname.setHorizontalAlignment(SwingConstants.CENTER);
			txt_nickname.setColumns(10);
			txt_nickname.setBounds(95, 42, 159, 20);
			txt_nickname.addKeyListener(this);
			getContentPane().add(txt_nickname);
			
			chk_spectate = new JCheckBox("Spectate?");
			chk_spectate.setMargin(new Insets(2, 2, 2, 0));
			chk_spectate.setBounds(256, 41, 82, 23);
			getContentPane().add(chk_spectate);
			
			JSeparator sep = new JSeparator();
			sep.setOrientation(SwingConstants.VERTICAL);
			sep.setBounds(84, 0, 2, 121);
			getContentPane().add(sep);
			
			JLabel lbl_credits = new JLabel("v2.0  -  by: Julius, Nico, ...");
			lbl_credits.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_credits.setBounds(95, 98, 243, 23);
			getContentPane().add(lbl_credits);
					
			BufferedImage bufferedImage = new BufferedImage(64, 64, 1);
		    bufferedImage.createGraphics().drawImage(((ImageIcon)lbl_image.getIcon()).getImage(), 0, 0, null);
		    ByteArrayOutputStream out = new ByteArrayOutputStream();
		    try{ImageIO.write(bufferedImage, "PNG", out);}catch(Exception ex){ex.printStackTrace();}
		    image = out.toByteArray();
			
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

		private void changeImage() {
		    JFileChooser filechooser= new JFileChooser();
		    FileFilter imageFilter = new FileNameExtensionFilter("Image Files (64x64 px)", ImageIO.getReaderFileSuffixes());
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
		        
						BufferedImage bufferedImage = new BufferedImage(64, 64, 1);
					    bufferedImage.createGraphics().drawImage((new ImageIcon(bi)).getImage(), 0, 0, null);
					    ByteArrayOutputStream out = new ByteArrayOutputStream();
					    try{ImageIO.write(bufferedImage, "PNG", out);}catch(Exception ex){ex.printStackTrace();}
					    image = out.toByteArray();
		            }
		        }
		        catch(Exception ex) {}
		        lbl_image.repaint();
		    }
		}

		private void checkFields() {
			boolean enable = true;
			if(!PATTERN.matcher(txt_address.getText()).matches()){
				enable = false;
			}
			
			try{int port = Integer.parseInt(txt_port.getText());
				if(port < 0 || port > 99999){
					enable = false;
				}
			}catch(Exception ex){enable = false;}
			
			if(txt_nickname.getText().length() == 0 || txt_nickname.getText().length() > 20){
				enable = false;
			}
			
			btn_connect.setEnabled(enable);
		}
		
		private void checkForPrompt(JTextField txt) {
			if (txt.getText().length() > 0) {
				if(txt.equals(txt_address)){
					lbl_address.setVisible(false);
				}else if(txt.equals(txt_port)){
					lbl_port.setVisible(false);
				}else if(txt.equals(txt_nickname)){
					lbl_nickname.setVisible(false);
				}
			}else{
				if(txt.equals(txt_address)){
					lbl_address.setVisible(true);
				}else if(txt.equals(txt_port)){
					lbl_port.setVisible(true);
				}else if(txt.equals(txt_nickname)){
					lbl_nickname.setVisible(true);
				}
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getSource() instanceof JTextField){
				JTextField txt = (JTextField)e.getSource();
				if(txt.equals(txt_address)){
					lbl_address.setVisible(false);
				}else if(txt.equals(txt_port)){
					lbl_port.setVisible(false);
				}else if(txt.equals(txt_nickname)){
					lbl_nickname.setVisible(false);
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			checkFields();
			if(e.getSource() instanceof JTextField){
				checkForPrompt((JTextField)e.getSource());
			}
		}

		@Override public void keyTyped(KeyEvent e) {}
		
		private void startGame() {   
			System.out.println("Adress: " + txt_address.getText() + ":" + txt_port.getText());
			System.out.println("Nickname: " + txt_nickname.getText());
			System.out.println("Spectate: " + chk_spectate.isSelected());
			System.out.println("Image: " + Arrays.toString(image));
		}
	}
