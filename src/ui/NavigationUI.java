package ui;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import managers.SystemManager;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NavigationUI extends JPanel 
{
	private JTextField tf_wins, tf_keys;
	private JLabel l_area, l_instructions;
	private JButton b_loc1, b_loc2, b_loc3, b_loc4, b_loc5, b_loc6, b_loc7, b_loc8, b_loc9, b_loc10,
					b_loc11, b_loc12, b_rest, b_boss, b_shop, b_save, b_quit, btnAreaInfo, b_inventory;
	private JLabel l_areaName, l_mobsDesc, l_missions;
	private JTextArea ta_status, ta_stats;

	private SystemManager systemManager;
	private NavigationHandler navigationHandler;
	
	public NavigationUI(SystemManager systemManager) 
	{
		this.systemManager = systemManager;
		navigationHandler = new NavigationHandler();
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		l_area = new JLabel("Area 1: Peiyuu Village");
		l_area.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		l_area.setBounds(30, 11, 285, 30);
		add(l_area);
		
		l_instructions = new JLabel("Click anywhere in the map to fight mobs.");
		l_instructions.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		l_instructions.setBounds(30, 39, 285, 30);
		add(l_instructions);
		
		b_loc1 = new JButton("Loc01");
		b_loc1.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		b_loc1.setBounds(70, 80, 125, 125);
		add(b_loc1);
		
		b_loc2 = new JButton("Loc02");
		b_loc2.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		b_loc2.setBounds(195, 80, 125, 125);
		add(b_loc2);
		
		b_loc3 = new JButton("Loc03");
		b_loc3.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		b_loc3.setBounds(320, 80, 125, 125);
		add(b_loc3);
		
		b_loc4 = new JButton("Loc04");
		b_loc4.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		b_loc4.setBounds(445, 80, 125, 125);
		add(b_loc4);
		
		b_loc5 = new JButton("Loc05");
		b_loc5.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		b_loc5.setBounds(70, 205, 125, 125);
		add(b_loc5);
		
		b_loc6 = new JButton("Loc06");
		b_loc6.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		b_loc6.setBounds(195, 205, 125, 125);
		add(b_loc6);
		
		b_loc7 = new JButton("Loc07");
		b_loc7.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		b_loc7.setBounds(320, 205, 125, 125);
		add(b_loc7);
		
		b_loc8 = new JButton("Loc08");
		b_loc8.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		b_loc8.setBounds(445, 205, 125, 125);
		add(b_loc8);
		
		b_loc9 = new JButton("Loc09");
		b_loc9.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		b_loc9.setBounds(70, 330, 125, 125);
		add(b_loc9);
		
		b_loc10 = new JButton("Loc10");
		b_loc10.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		b_loc10.setBounds(195, 330, 125, 125);
		add(b_loc10);
		
		b_loc11 = new JButton("Loc11");
		b_loc11.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		b_loc11.setBounds(320, 330, 125, 125);
		add(b_loc11);
		
		b_loc12 = new JButton("Loc12");
		b_loc12.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		b_loc12.setBounds(445, 330, 125, 125);
		add(b_loc12);
		
		l_areaName = new JLabel("Area 1");
		l_areaName.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		l_areaName.setBounds(102, 466, 56, 30);
		add(l_areaName);
		
		l_mobsDesc = new JLabel("Mob Level 1 to 5");
		l_mobsDesc.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		l_mobsDesc.setBounds(375, 466, 148, 30);
		add(l_mobsDesc);
		
		b_rest = new JButton("Rest to Inn");
		b_rest.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		b_rest.setBounds(30, 525, 170, 50);
		add(b_rest);
		
		b_shop = new JButton("Shop");
		b_shop.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		b_shop.setBounds(236, 525, 170, 50);
		add(b_shop);
		
		b_boss = new JButton("Fight Boss");
		b_boss.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		b_boss.setBounds(445, 525, 170, 50);
		add(b_boss);
		
		b_save = new JButton("Save Game");
		b_save.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		b_save.setBounds(655, 525, 170, 50);
		add(b_save);
		
		b_quit = new JButton("Quit");
		b_quit.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		b_quit.setBounds(863, 525, 170, 50);
		add(b_quit);
		
		b_inventory = new JButton("Inventory");
		b_inventory.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		b_inventory.setBounds(655, 466, 170, 50);
		add(b_inventory);
		
		btnAreaInfo = new JButton("Area Info");
		btnAreaInfo.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		btnAreaInfo.setBounds(863, 466, 170, 50);
		add(btnAreaInfo);
		
		ta_status = new JTextArea();
		ta_status.setText("Name\t\tLv\r\nHP\t\tSP\r\nAu");
		ta_status.setEditable(false);
		ta_status.setHighlighter(null);
		ta_status.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		ta_status.setBounds(655, 106, 376, 64);
		add(ta_status);
		
		ta_stats = new JTextArea();
		ta_stats.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		ta_stats.setEditable(false);
		ta_stats.setHighlighter(null);
		ta_stats.setBounds(655, 171, 376, 114);
		add(ta_stats);
		
		l_missions = new JLabel("Missions");
		l_missions.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		l_missions.setBounds(655, 312, 80, 30);
		add(l_missions);
		
		tf_wins = new JTextField();
		tf_wins.setEditable(false);
		tf_wins.setHighlighter(null);
		tf_wins.setText("Battle Wins:");
		tf_wins.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		tf_wins.setBounds(655, 342, 376, 38);
		add(tf_wins);
		tf_wins.setColumns(10);
		
		tf_keys = new JTextField();
		tf_keys.setEditable(false);
		tf_keys.setHighlighter(null);
		tf_keys.setText("Boss Keys:");
		tf_keys.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		tf_keys.setColumns(10);
		tf_keys.setBounds(655, 391, 376, 38);
		add(tf_keys);
		
		b_shop.addActionListener(navigationHandler);
		b_save.addActionListener(navigationHandler);
		b_quit.addActionListener(navigationHandler);
		b_inventory.addActionListener(navigationHandler);
		b_loc1.addActionListener(navigationHandler);
		b_loc2.addActionListener(navigationHandler);
		b_loc3.addActionListener(navigationHandler);
		b_loc4.addActionListener(navigationHandler);
		b_loc5.addActionListener(navigationHandler);
		b_loc6.addActionListener(navigationHandler);
		b_loc7.addActionListener(navigationHandler);
		b_loc8.addActionListener(navigationHandler);
		b_loc9.addActionListener(navigationHandler);
		b_loc10.addActionListener(navigationHandler);
		b_loc11.addActionListener(navigationHandler);
		b_loc12.addActionListener(navigationHandler);
		b_boss.addActionListener(navigationHandler);
	}
	
	private class NavigationHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String action = e.getActionCommand();
			if(action.equals("Shop"))
				systemManager.showShopUI();
			else if(action.equals("Inventory"))
				systemManager.showInventoryUI();
			else if(action.equals("Save Game"))
				systemManager.showLoadSaveUI(false);
			else if(action.equals("Quit"))
				System.exit(0);
			else if(action.substring(0, 3).equals("Loc"))
				systemManager.showBattleUI();
			else if(action.equals("Fight Boss"))
				systemManager.showBattleUI();
		}
	}

}
