package managers;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import domain.BossMonster;
import domain.GameItem;
import domain.MobMonster;
import domain.Skill;

public class StatsManager 
{
	/*
	 * The StatsManager stores the raw base stats of players, mobs, and bosses.
	 * It also includes the XP chart for Swordsman and Mage.
	 */

	private Scanner in;
	private double[] swordsmanXPChart, mageXPChart;
	private ArrayList<Integer[]> swordsmanBaseStats, mageBaseStats, mobLevelRanges;
	private ArrayList<String> mobStatsRaw, bossStatsRaw;
	private ArrayList<Skill> swordsmanSkillSet, mageSkillSet;
	private ArrayList<GameItem> itemList;
	private LanguageManager languageManager;
	
	public StatsManager()
	{
		languageManager = new LanguageManager("item");
		scanSwordsmanXPChart();
		scanMageXPChart();
		scanSwordsmanBaseStats();
		scanMageBaseStats();
		scanSwordsmanSkillSet();
		scanMageSkillSet();
		scanMobBaseStats();
		scanBossBaseStats();
		scanMobLevelRanges();
		scanItems();
	}

	private void scanItems() 
	{
		try
		{
			in = new Scanner(new File("resources/data/item_list.fae"));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		String rawInfo, splitInfo[];
		GameItem gameItem;
		itemList = new ArrayList<GameItem>();
		while(in.hasNextLine())
		{
			rawInfo = new String(in.nextLine());
			splitInfo = rawInfo.split(",");
			gameItem = new GameItem();
			gameItem.setItemID(splitInfo[0]);
			gameItem.setAreaAvailable(Integer.parseInt(splitInfo[1]));
			gameItem.setItemName(splitInfo[2]);
			gameItem.setBuyingValue(Double.parseDouble(splitInfo[3]));
			gameItem.setSellingValue(Double.parseDouble(splitInfo[4]));
			gameItem.setEffectHPtoPlayer(Double.parseDouble(splitInfo[5]));
			gameItem.setEffectSPtoPlayer(Double.parseDouble(splitInfo[6]));
			gameItem.setEffectATKtoPlayer(Double.parseDouble(splitInfo[7]));
			gameItem.setEffectDEFtoPlayer(Double.parseDouble(splitInfo[8]));
			gameItem.setEffectSPCtoPlayer(Double.parseDouble(splitInfo[9]));
			gameItem.setEffectAGItoPlayer(Double.parseDouble(splitInfo[10]));
			gameItem.setEffectCRTtoPlayer(Double.parseDouble(splitInfo[11]));
			gameItem.setEffectHPtoOpponent(Double.parseDouble(splitInfo[12]));
			gameItem.setEffectSPtoOpponent(Double.parseDouble(splitInfo[13]));
			gameItem.setEffectATKtoOpponent(Double.parseDouble(splitInfo[14]));
			gameItem.setEffectDEFtoOpponent(Double.parseDouble(splitInfo[15]));
			gameItem.setEffectSPCtoOpponent(Double.parseDouble(splitInfo[16]));
			gameItem.setEffectAGItoOpponent(Double.parseDouble(splitInfo[17]));
			gameItem.setEffectCRTtoOpponent(Double.parseDouble(splitInfo[18]));
			gameItem.setItemDesc(languageManager.getItemDescription(gameItem.getItemID()));
			itemList.add(gameItem);
		}
	}
	
	public GameItem getGameItem(String itemName)
	{
		GameItem itemToReturn = new GameItem();
		for(GameItem item: itemList)
		{
			if(item.getItemName().equals(itemName))
				itemToReturn = item;
		}
		return itemToReturn;
	}

	public ArrayList<GameItem> getPurchasableItemsOnArea(int area)
	{
		ArrayList<GameItem> listToReturn = new ArrayList<GameItem>();
		for(GameItem item: itemList)
		{
			if(item.getAreaAvailable() == area)
				listToReturn.add(item);
		}
		return listToReturn;
	}
	
	public ArrayList<GameItem> getDefaultInventory()
	{
		ArrayList<GameItem> listToReturn = new ArrayList<GameItem>();
		for(GameItem item: itemList)
		{
			item.setQuantity(0);
			listToReturn.add(item);
		}
		return listToReturn;
	}
	
	private void scanMobLevelRanges() 
	{
		try
		{
			in = new Scanner(new File("resources/data/moblevelrange.fae"));
		} catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		Integer[] range;
		String[] splitInfo;
		mobLevelRanges = new ArrayList<Integer[]>();
		while(in.hasNext())
		{
			splitInfo = in.next().split(",");
			range = new Integer[2];
			for(int i = 0; i < splitInfo.length; i++)
				range[i] = Integer.parseInt(splitInfo[i]);
			mobLevelRanges.add(range);
		}		
	}
	
	public int getMinMobLevel(int area)
	{
		return mobLevelRanges.get(area-1)[0];
	}
	public int getMaxMobLevel(int area)
	{
		return mobLevelRanges.get(area-1)[1];
	}

	private void scanMageSkillSet() 
	{
		try
		{
			in = new Scanner(new File("resources/data/skillset_mage.fae"));
		} catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		String rawInfo, splitInfo[];
		Skill skill;
		mageSkillSet = new ArrayList<Skill>();
		while(in.hasNextLine())
		{
			rawInfo = new String(in.nextLine());
			splitInfo = rawInfo.split(",");
			skill = new Skill();
			skill.setSkillName(splitInfo[0]);
			skill.setRequiredLevel(Integer.parseInt(splitInfo[1]));
			skill.setSkillHPCost(Double.parseDouble(splitInfo[2]));
			skill.setSkillSPCost(Double.parseDouble(splitInfo[3]));
			mageSkillSet.add(skill);
		}
	}

	private void scanSwordsmanSkillSet()
	{
		try
		{
			in = new Scanner(new File("resources/data/skillset_swordsman.fae"));
		} catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		String rawInfo, splitInfo[];
		Skill skill;
		swordsmanSkillSet = new ArrayList<Skill>();
		while(in.hasNextLine())
		{
			rawInfo = new String(in.nextLine());
			splitInfo = rawInfo.split(",");
			skill = new Skill();
			skill.setSkillName(splitInfo[0]);
			skill.setRequiredLevel(Integer.parseInt(splitInfo[1]));
			skill.setSkillHPCost(Double.parseDouble(splitInfo[2]));
			skill.setSkillSPCost(Double.parseDouble(splitInfo[3]));
			swordsmanSkillSet.add(skill);
		}
	}

	private void scanSwordsmanXPChart() 
	{
		try 
		{
			in = new Scanner(new File("resources/data/swordsman_xpchart.fae"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		swordsmanXPChart = new double[100];
		int index = 0;
		while(in.hasNext())
		{
			swordsmanXPChart[index] = Integer.parseInt(in.next());
			index++;
		}
	}

	public int getPlayerLevel(String battleClass, double xp)
	{
		int toReturn;
		if(battleClass.equals("Swordsman"))
		{
			for(toReturn = 0; toReturn < swordsmanXPChart.length; toReturn++)
			{
				if(xp < swordsmanXPChart[toReturn])
				{
					toReturn++;
					break;
				}
			}
		}
		else
		{
			for(toReturn = 0; toReturn < mageXPChart.length; toReturn++)
			{
				if(xp < mageXPChart[toReturn])
				{
					toReturn++;
					break;
				}
			}
		}
		return toReturn;
	}

	public double getPlayerXPtoNextLevel(String battleClass, int level)
	{
		double toReturn = 0.0;
		if(battleClass.equals("Swordsman"))
		{
			if(level < 101)
				toReturn = swordsmanXPChart[level-1];
			else
				toReturn = 999999999;
		}
		else
		{
			if(level < 101)
				toReturn = mageXPChart[level-1];
			else
				toReturn = 999999999;
		}
		return toReturn;
	}

	private void scanMageXPChart() 
	{
		try 
		{
			in = new Scanner(new File("resources/data/mage_xpchart.fae"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mageXPChart = new double[100];
		int index = 0;
		while(in.hasNext())
		{
			mageXPChart[index] = Integer.parseInt(in.next());
			index++;
		}
	}

	private void scanSwordsmanBaseStats()
	{
		try
		{
			in = new Scanner(new File("resources/data/basestats_swordsman.fae"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Integer[] stats;
		String[] statsInString;
		swordsmanBaseStats = new ArrayList<Integer[]>();
		while(in.hasNext())
		{
			statsInString = in.next().split(",");
			stats = new Integer[7];
			for(int i = 0; i < stats.length; i++)
				stats[i] = Integer.parseInt(statsInString[i]);
			swordsmanBaseStats.add(stats);
		}
	}

	private void scanMageBaseStats() 
	{
		try
		{
			in = new Scanner(new File("resources/data/basestats_mage.fae"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Integer[] stats;
		String[] statsInString;
		mageBaseStats = new ArrayList<Integer[]>();
		while(in.hasNext())
		{
			statsInString = in.next().split(",");
			stats = new Integer[7];
			for(int i = 0; i < stats.length; i++)
				stats[i] = Integer.parseInt(statsInString[i]);
			mageBaseStats.add(stats);
		}
	}

	public Integer getPlayerHPbaseStat(String battleClass, int level)
	{
		int toReturn = 0;
		if(battleClass.equals("Swordsman"))
			toReturn = swordsmanBaseStats.get(level-1)[0];
		else
			toReturn = mageBaseStats.get(level-1)[0];
		return toReturn;
	}
	
	public Integer getPlayerSPbaseStat(String battleClass, int level)
	{
		int toReturn = 0;
		if(battleClass.equals("Swordsman"))
			toReturn = swordsmanBaseStats.get(level-1)[1];
		else
			toReturn = mageBaseStats.get(level-1)[1];
		return toReturn;
	}
	
	public Integer getPlayerATKbaseStat(String battleClass, int level)
	{
		int toReturn = 0;
		if(battleClass.equals("Swordsman"))
			toReturn = swordsmanBaseStats.get(level-1)[2];
		else
			toReturn = mageBaseStats.get(level-1)[2];
		return toReturn;
	}
	
	public Integer getPlayerDEFbaseStat(String battleClass, int level)
	{
		int toReturn = 0;
		if(battleClass.equals("Swordsman"))
			toReturn = swordsmanBaseStats.get(level-1)[3];
		else
			toReturn = mageBaseStats.get(level-1)[3];
		return toReturn;
	}
	
	public Integer getPlayerSPCbaseStat(String battleClass, int level)
	{
		int toReturn = 0;
		if(battleClass.equals("Swordsman"))
			toReturn = swordsmanBaseStats.get(level-1)[4];
		else
			toReturn = mageBaseStats.get(level-1)[4];
		return toReturn;
	}
	
	public Integer getPlayerAGIbaseStat(String battleClass, int level)
	{
		int toReturn = 0;
		if(battleClass.equals("Swordsman"))
			toReturn = swordsmanBaseStats.get(level-1)[5];
		else
			toReturn = mageBaseStats.get(level-1)[5];
		return toReturn;
	}
	
	public Integer getPlayerCRTbaseStat(String battleClass, int level)
	{
		int toReturn = 0;
		if(battleClass.equals("Swordsman"))
			toReturn = swordsmanBaseStats.get(level-1)[6];
		else
			toReturn = mageBaseStats.get(level-1)[6];
		return toReturn;
	}
	
	private void scanMobBaseStats() 
	{
		try
		{
			in = new Scanner(new File("resources/data/basestats_mobs.fae"));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		String rawStatsInString;
		mobStatsRaw = new ArrayList<String>();
		while(in.hasNextLine())
		{
			rawStatsInString = new String(in.nextLine());
			mobStatsRaw.add(rawStatsInString);
		}
	}

	public MobMonster getMobMonster(int mobID, int level)
	{
		MobMonster mobMonsterToReturn = new MobMonster();
		String[] rawStats = mobStatsRaw.get(mobID).split(",");
		mobMonsterToReturn.setMobID(mobID);
		mobMonsterToReturn.setLevel(level);
		mobMonsterToReturn.setFirstArea(Integer.parseInt(rawStats[0]));
		mobMonsterToReturn.setName(rawStats[1]);
		mobMonsterToReturn.setBaseHP(Integer.parseInt(rawStats[2]));
		mobMonsterToReturn.setBaseSP(Integer.parseInt(rawStats[3]));
		mobMonsterToReturn.setBaseATK(Integer.parseInt(rawStats[4]));
		mobMonsterToReturn.setBaseDEF(Integer.parseInt(rawStats[5]));
		mobMonsterToReturn.setBaseSPC(Integer.parseInt(rawStats[6]));
		mobMonsterToReturn.setBaseAGI(Integer.parseInt(rawStats[7]));
		mobMonsterToReturn.setBaseCRT(Integer.parseInt(rawStats[8]));
		mobMonsterToReturn.setChanceAttack(Double.parseDouble(rawStats[9]));
		mobMonsterToReturn.setChanceDefend(Double.parseDouble(rawStats[10]));
		mobMonsterToReturn.setChanceSkill(Double.parseDouble(rawStats[11]));
		mobMonsterToReturn.setChanceFlee(Double.parseDouble(rawStats[12]));
		mobMonsterToReturn.setChanceBossKeyDrop(Double.parseDouble(rawStats[13]));
		mobMonsterToReturn.setBaseXPYield(Double.parseDouble(rawStats[14]));
		mobMonsterToReturn.setBaseAuYield(Double.parseDouble(rawStats[15]));
		mobMonsterToReturn.setSkill(new Skill(rawStats[16], 0.0, Double.parseDouble(rawStats[17]), 0));
		
		return mobMonsterToReturn;
	}
	
	private void scanBossBaseStats()
	{
		try
		{
			in = new Scanner(new File("resources/data/basestats_bosses.fae"));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		String rawStatsInString;
		bossStatsRaw = new ArrayList<String>();
		while(in.hasNextLine())
		{
			rawStatsInString = new String(in.nextLine());
			bossStatsRaw.add(rawStatsInString);
		}
	}
	
	public BossMonster getBossMonster(int bossID)
	{
		BossMonster bossMonsterToReturn = new BossMonster();
		String[] rawStats = bossStatsRaw.get(bossID).split(",");
		bossMonsterToReturn.setBossID(bossID);
		bossMonsterToReturn.setName(rawStats[0]);
		bossMonsterToReturn.setLevel(Integer.parseInt(rawStats[1]));
		bossMonsterToReturn.setBaseHP(Integer.parseInt(rawStats[2]));
		bossMonsterToReturn.setBaseSP(Integer.parseInt(rawStats[3]));
		bossMonsterToReturn.setSecondWindHP(Double.parseDouble(rawStats[4]));
		bossMonsterToReturn.setSecondWindSP(Double.parseDouble(rawStats[5]));
		bossMonsterToReturn.setBaseATK(Integer.parseInt(rawStats[6]));
		bossMonsterToReturn.setBaseDEF(Integer.parseInt(rawStats[7]));
		bossMonsterToReturn.setBaseSPC(Integer.parseInt(rawStats[8]));
		bossMonsterToReturn.setBaseAGI(Integer.parseInt(rawStats[9]));
		bossMonsterToReturn.setBaseCRT(Integer.parseInt(rawStats[10]));
		bossMonsterToReturn.setChanceAttack(Double.parseDouble(rawStats[11]));
		bossMonsterToReturn.setChanceDefend(Double.parseDouble(rawStats[12]));
		bossMonsterToReturn.setChanceSkill(Double.parseDouble(rawStats[13]));
		bossMonsterToReturn.setChanceHeal(Double.parseDouble(rawStats[14]));
		bossMonsterToReturn.setChanceAttackWind2(Double.parseDouble(rawStats[15]));
		bossMonsterToReturn.setChanceDefendWind2(Double.parseDouble(rawStats[16]));
		bossMonsterToReturn.setChanceSkillWind2(Double.parseDouble(rawStats[17]));
		bossMonsterToReturn.setDefXPYield(Double.parseDouble(rawStats[18]));
		bossMonsterToReturn.setDefAuYield(Double.parseDouble(rawStats[19]));
		bossMonsterToReturn.setSkill(new Skill(rawStats[20], 0.0, Double.parseDouble(rawStats[21]), 0));
		bossMonsterToReturn.setMissionBossKeys(Integer.parseInt(rawStats[22]));
		bossMonsterToReturn.setMissionWins(Integer.parseInt(rawStats[23]));
		return bossMonsterToReturn;
	}

	public ArrayList<Skill> getSkillSet(String battleClass) 
	{
		ArrayList<Skill> toReturn;
		if(battleClass.equals("Swordsman"))
			toReturn = swordsmanSkillSet;
		else
			toReturn = mageSkillSet;
		return toReturn;
	}
}