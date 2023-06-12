package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.*;

import constant.CButton;
import constant.Constant;

public class MUI extends JFrame{
	private CPU cpu;
	private Memory memory;
	private static final long serialVersionUID = 1L;
	private Vector<File> files;
	private boolean exitButton;
	private JLabel list;
	
	public MUI() {
		files = new Vector<>();
		exitButton = true;
		
		this.setSize(600, 600); // 너비, 길이
		this.setLocationRelativeTo(null); // 가운데로 맞추기
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ActionHandler actionHandler = new ActionHandler();
		JPanel btnPanel = new JPanel();
		for(CButton button : CButton.values()) {
			JButton btn = new JButton(button.getLabel());
			btn.setActionCommand(button.getName());
			btn.addActionListener(actionHandler);
			btnPanel.add(btn);
		}
		
		JPanel showPanel = new JPanel();
		showPanel.setBackground(Color.white);
		
		list = new JLabel();
		list.setHorizontalAlignment(JLabel.CENTER);
		
		JScrollPane jScrollPane = new JScrollPane(list);
		jScrollPane.setPreferredSize(new Dimension(500, 450));
		showPanel.add(jScrollPane);
		
		this.add(btnPanel, BorderLayout.NORTH);
		this.add(showPanel, BorderLayout.CENTER);
		this.setVisible(true);
		
	}
	
	public void initialize() {
		cpu = new CPU();
		memory = new Memory();
		cpu.associate(memory);
		memory.associate(cpu.mar, cpu.mbr);
	}
	

	public void run() {
		while(exitButton) {
			if(!files.isEmpty()) {
				this.initialize();
				File file = files.remove(0);
				memory.setFile(file);
				memory.parse();
				list.setText(cpu.start(file.getName()));
			}
		}
		System.exit(0);
	}

	
	// open
		private File open() {
			JFileChooser fileChooser = new JFileChooser(new File(System.getProperty(Constant.UI.userOpenFileDir)));
			int ret = fileChooser.showOpenDialog(this);
			if (ret == JFileChooser.APPROVE_OPTION) {
				return fileChooser.getSelectedFile();
			}
			return null;
			
		}
	
	public class ActionHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			File file = null;
			if(e.getActionCommand().equals(CButton.Select.getName())) {
				file = open();
			}else if(e.getActionCommand().equals(CButton.Exe.getName())) {
				file = new File(Constant.UI.code + CButton.Exe.getName());
			}else if(e.getActionCommand().equals(CButton.Exe1.getName())) {
				file = new File(Constant.UI.code + CButton.Exe1.getName());
			}else if(e.getActionCommand().equals(CButton.Exit.getName())) {
				exitButton = false;
			}
			if(file != null) {
				files.add(file);
			}
		}
	}



}
