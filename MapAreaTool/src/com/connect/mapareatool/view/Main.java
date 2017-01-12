package com.connect.mapareatool.view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.connect.mapareatool.Components.CustomImage;
import com.connect.mapareatool.Components.ImageListener;
import com.connect.mapareatool.model.MapAreas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private BufferedImage image;
	private JScrollPane imageScrollPane;
	private Image img;
	private MapAreas areas;

	public MapAreas getAreas() {
		return areas;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		//intit model
		this.areas = new MapAreas();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
		setTitle("Map Area Tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 764, 596);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenu mnExit = new JMenu("Exit");
		mnFile.add(mnExit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenu mnAbout = new JMenu("About ");
		mnHelp.add(mnAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Image", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Area", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)))
				);

		try {				
			image =  ImageIO.read(new File("src/map.png"));	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//ImageView
		CustomImage bild = new CustomImage(image);
		bild.addMouseListener(new ImageListener(this,bild,areas));
		imageScrollPane = new JScrollPane(bild);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
				.addComponent(imageScrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
				.addComponent(imageScrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
				);
		panel.setLayout(gl_panel);

		JButton btnExport = new JButton("Export");
		btnExport.addMouseListener(new ExportListener(areas));
		btnExport.setIcon(new ImageIcon(Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		toolBar.add(btnExport);
		contentPane.setLayout(gl_contentPane);	

	}
	
}
