/*
 * Copyright (C) 2016  Tobias Bielefeld
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * If you want to contact me, send me an e-mail at tobias.bielefeld@gmail.com
 */

package de.tobiasbielefeld.solitaire;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;

import de.tobiasbielefeld.solitaire.games.AcesUp;
import de.tobiasbielefeld.solitaire.games.Calculation;
import de.tobiasbielefeld.solitaire.games.Canfield;
import de.tobiasbielefeld.solitaire.games.FortyEight;
import de.tobiasbielefeld.solitaire.games.Freecell;
import de.tobiasbielefeld.solitaire.games.Game;
import de.tobiasbielefeld.solitaire.games.Golf;
import de.tobiasbielefeld.solitaire.games.GrandfathersClock;
import de.tobiasbielefeld.solitaire.games.Gypsy;
import de.tobiasbielefeld.solitaire.games.Klondike;
import de.tobiasbielefeld.solitaire.games.Maze;
import de.tobiasbielefeld.solitaire.games.Mod3;
import de.tobiasbielefeld.solitaire.games.NapoleonsTomb;
import de.tobiasbielefeld.solitaire.games.Pyramid;
import de.tobiasbielefeld.solitaire.games.SimpleSimon;
import de.tobiasbielefeld.solitaire.games.Spider;
import de.tobiasbielefeld.solitaire.games.TriPeaks;
import de.tobiasbielefeld.solitaire.games.Vegas;
import de.tobiasbielefeld.solitaire.games.Yukon;

import static de.tobiasbielefeld.solitaire.SharedData.prefs;

/**
 * Everything about loading a game should be here. If you want to add a game, just expand the switch
 * statements with a new case. But think of the order! Every game is alphabetically ordered.
 * The order is important for the methods which returns ArrayLists.
 */

public class LoadGame {

    private String gameName;
    private String sharedPrefName;
    private ArrayList<AllGameInformation> allGameInformation = new ArrayList<>();
    private int GAME_COUNT;

    /**
     * load the game class and set the shown name and the name used for the sharedPref of the
     * current game.
     *
     * @param activity The activity to get the strings from the xml file
     * @param index The index of the game to start
     */
    public Game loadClass(Activity activity, int index) {

        sharedPrefName = allGameInformation.get(index).getSharedPrefName();
        gameName = allGameInformation.get(index).getName(activity.getResources());

        switch (index) {
            default: Log.e("LoadGame.loadClass()", "Your games seems not to be added here?");//fallthrough
            case 0: return new AcesUp();
            case 1: return new Calculation();
            case 2: return new Canfield();
            case 3: return new FortyEight();
            case 4: return new Freecell();
            case 5: return new Golf();
            case 6: return new GrandfathersClock();
            case 7: return new Gypsy();
            case 8: return new Klondike();
            case 9: return new Maze();
            case 10: return new Mod3();
            case 11: return new NapoleonsTomb();
            case 12: return new Pyramid();
            case 13:return new SimpleSimon();
            case 14:return new Spider();
            case 15:return new TriPeaks();
            case 16:return new Vegas();
            case 17:return new Yukon();
        }
    }

    /**
     * Insert new games here and in loadClass(). The order is very important, so don't change it!
     * The resource id points to the name of the game, so it can be translated. The second parameter
     * is the prefix for the game saves (like order of the cards). It uses a separate sharedPref for
     * each game. The string with the game name have to match the sharedPref name like
     * "games_<sharedPrefName>". Look at the other game names for a hint! It is important for
     * the manual pages. It is also the prefix for the manual entries. So use it when writing manual
     * entries!
     *
     * If you add a game at the end, no further actions has to be done, expect updating the game
     * selector images and adding a manual entry. If you add it somewhere else (eg to get an
     * alphabetical default order) you need to update getMenuShownList(), getOrderedGameList() and
     * loadClass() !
     */
    public void loadAllGames(){
        allGameInformation.clear();

        allGameInformation.add(new AllGameInformation(R.string.games_AcesUp,"AcesUp"));
        allGameInformation.add(new AllGameInformation(R.string.games_Calculation,"Calculation"));
        allGameInformation.add(new AllGameInformation(R.string.games_Canfield,"Canfield"));
        allGameInformation.add(new AllGameInformation(R.string.games_FortyEight,"FortyEight"));
        allGameInformation.add(new AllGameInformation(R.string.games_Freecell,"Freecell"));
        allGameInformation.add(new AllGameInformation(R.string.games_Golf,"Golf"));
        allGameInformation.add(new AllGameInformation(R.string.games_GrandfathersClock,"GrandfathersClock"));
        allGameInformation.add(new AllGameInformation(R.string.games_Gypsy,"Gypsy"));
        allGameInformation.add(new AllGameInformation(R.string.games_Klondike,"Klondike"));
        allGameInformation.add(new AllGameInformation(R.string.games_Maze,"Maze"));
        allGameInformation.add(new AllGameInformation(R.string.games_mod3,"mod3"));
        allGameInformation.add(new AllGameInformation(R.string.games_NapoleonsTomb,"NapoleonsTomb"));
        allGameInformation.add(new AllGameInformation(R.string.games_Pyramid,"Pyramid"));
        allGameInformation.add(new AllGameInformation(R.string.games_SimpleSimon,"SimpleSimon"));
        allGameInformation.add(new AllGameInformation(R.string.games_Spider,"Spider"));
        allGameInformation.add(new AllGameInformation(R.string.games_TriPeaks,"TriPeaks"));
        allGameInformation.add(new AllGameInformation(R.string.games_Vegas,"Vegas"));
        allGameInformation.add(new AllGameInformation(R.string.games_Yukon,"Yukon"));

        GAME_COUNT = allGameInformation.size();
    }

    /**
     * Gets the list of shown games in the game selection menu.
     * The order of the game is the DEFAULT PREF_KEY_ORDER!
     *
     * If you add a game, the list lacks the newly added game. so you can insert it at the right
     * position, or it will be automatically added at the end.
     *
     * 1 stands for show, 0 for not showing.
     *
     * @return the list of shown/not shown in the game selection menu
     */
    public ArrayList<Integer> getMenuShownList(){
        ArrayList<Integer> result = prefs.getSavedMenuGamesList();

        /*
         * If added more games, insert them here in the correct order. Don't forget to add it also
         * in getOrderedGameList()! If the new game is at the end of the game list, you don't
         * need to do anything, it will be appended automatically.
         */

        if (result.size() == 12) {                                                                  //new canfield game
            result.add(1, 1);
        }
        if (result.size() == 13) {                                                                  //new grand fathers clock game
            result.add(5, 1);
        }
        if (result.size() == 14) {                                                                  //new vegas game
            result.add(13, 1);
        }
        if (result.size() == 15) {                                                                  //new calculation game
            result.add(1, 1);
        }
        if (result.size() == 16) {                                                                  //new Napoleons Tomb game
            result.add(10, 1);
        }

        if (result.size() < getGameCount()){
            for (int i=result.size();i<getGameCount();i++){
                result.add(1);
            }
        }

        return result;
    }

    /**
     * Returns the game list as integers in order of the user settings. If the user didn't set up
     * a custom order yet, the default order will be returned. If there was added a new game,
     * it will the added at the end.
     *
     * YOU DONT NEED TO ADD A NEW GAME HERE!
     *
     * @return the game list in order of the user settings.
     */
    public ArrayList<Integer> getOrderedGameList(){
        ArrayList<Integer> result = prefs.getSavedMenuOrderList();

        if (result.isEmpty()){                                                                      //get default order
            for (int i=0;i<getGameCount();i++){
                result.add(i);
            }
        }

       /*
         * If added more games, insert them here in the correct order. Don't forget to add it also
         * in getMenuShownList()! If the new game is at the end of the game list, you don't
         * need to do anything, it will be appended automatically.
         *
         * This is an example, if a new game has been added at the second last position. In the
         * ordered game list, it has to appear at the very end:
         */
        if (result.size()==15){                                                                     //added Calculation at index 1
            result.add(1,result.size());
        }

        if (result.size() < getGameCount()){                                                        //add new games at the end
            for (int i=result.size();i<getGameCount();i++){
                result.add(i);
            }
        }

        return result;
    }

    public int getGameCount(){
        return GAME_COUNT;
    }

    /**
     * Uses the game information to return the DEFAULT order of the games.
     *
     *
     * @param res   Resources to load the strings
     * @return      the default game name list as string array
     */
    public String[] getDefaultGameNameList(Resources res){
        String[] list = new String[allGameInformation.size()];

        for (int i = 0; i< allGameInformation.size(); i++){
            list[i] = allGameInformation.get(i).getName(res);
        }

        return list;
    }

    /**
     * Uses the game information to return the ORDERED names of the games.
     *
     *
     * @param res   Resources to load the strings
     * @return      the default game name list as string array list
     */
    public ArrayList<String> getOrderedGameNameList(Resources res){

        ArrayList<Integer> savedList = getOrderedGameList();
        ArrayList<String> returnList = new ArrayList<>(allGameInformation.size());
        String[] defaultList = getDefaultGameNameList(res);

        for (int i=0;i<getGameCount();i++){
            returnList.add(defaultList[savedList.indexOf(i)]);
        }

        return returnList;
    }

    /**
     * Returns a list of all the sharedPref names
     *
     * @return      the shared pref name list as string array
     */
    public String[] getSharedPrefNameList(){
        String[] list = new String[allGameInformation.size()];

        for (int i = 0; i< allGameInformation.size(); i++){
            list[i] = allGameInformation.get(i).getSharedPrefName();
        }

        return list;
    }

    /**
     * Returns a list of all the sharedPref names but in the same order as the ordered game list!
     *
     * @return      the shared pref name list as string array
     */
    public ArrayList<String> getOrderedSharedPrefNameList(){
        ArrayList<Integer> savedList = getOrderedGameList();
        ArrayList<String> returnList = new ArrayList<>(allGameInformation.size());

        for (int i=0;i<getGameCount();i++){
            returnList.add(allGameInformation.get(savedList.indexOf(i)).getSharedPrefName());
        }

        return returnList;
    }

    /**
     * Returns the shared pref prefix of the given game. Used in the manual, so on a click to the
     * game entries, the resources are loaded using the shared pref prefix in the form of eg:
     * manual_<gamePrefix>_structure or manual_<gamePrefix>_objective
     *
     * @param index Index of the game as in the default order
     * @return      the shared pref prefix string
     */
    public String getSharedPrefNameOfGame(int index){
        return allGameInformation.get(index).getSharedPrefName();
    }

    public String getGameName() {
        return gameName;
    }

    public String getGameName(Resources res, int index) {
        return allGameInformation.get(index).getName(res);
    }

    public String getSharedPrefName() {
        return sharedPrefName;
    }

    /**
     * little class to collect all needed game information in one array list.
     */
    private class AllGameInformation {

        private int shownNameResID;
        private String sharedPrefName;

        AllGameInformation(int shownNameResID, String sharedPrefName){
            this.shownNameResID = shownNameResID;
            this.sharedPrefName = sharedPrefName;
        }

        public String getName(Resources res){
            return res.getString(shownNameResID);
        }

        public String getSharedPrefName(){
            return sharedPrefName;
        }
    }
}
