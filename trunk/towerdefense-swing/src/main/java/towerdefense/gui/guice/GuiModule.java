package towerdefense.gui.guice;


import javax.inject.Singleton;

import towerdefense.gui.MapEditionDialog;
import towerdefense.gui.StartGameDialog;
import towerdefense.gui.actions.MapEditAction;
import towerdefense.gui.actions.NewMapAction;
import towerdefense.gui.actions.StartGamePlayAction;
import ca.concordia.soen6441.guice.TowerDefenseModule;

import com.google.inject.AbstractModule;

public class GuiModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new TowerDefenseModule());

		bind(MapEditionDialog.class).in(Singleton.class);
//		MapEditionDialog mapEditionDialog = new MapEditionDialog(gameMapDao);
//		mapEditionDialog.setVisible(false);
		bind(MapEditAction.class).in(Singleton.class);
		bind(StartGamePlayAction.class).in(Singleton.class);
		bind(NewMapAction.class).in(Singleton.class);
		bind(StartGameDialog.class).in(Singleton.class);
//		MapEditAction mapEditAction = new MapEditAction(mapEditionDialog);
//		StartGamePlayAction startGamePlayAction = new StartGamePlayAction();
//		NewMapAction newMapAction = new NewMapAction(mapEditionDialog);
//		StartGameDialog startDialog = new StartGameDialog(gameMapDao, newMapAction, mapEditAction, startGamePlayAction);		
//        SaveMapAction saveMapAction = new SaveMapAction(mapEditionDialog, startDialog);
//        mapEditionDialog.setStartGameDialog(startDialog);
	}

}
