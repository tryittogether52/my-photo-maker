package com.wsc.photomaker.app;

import java.util.ArrayList;
import java.util.List;

import com.wsc.photomaker.controller.AbstractManager;
import com.wsc.photomaker.controller.FileManagerService;
import com.wsc.photomaker.controller.ResourceManager;
import com.wsc.photomaker.controller.ScreenManager;
import com.wsc.photomaker.model.KModel;
import com.wsc.photomaker.model.KRuntimeModel;

public class KFrontManager {

	List<AbstractManager> managersList;

	private static KFrontManager instance;

	private KFrontManager() {
		managersList = new ArrayList<AbstractManager>();

		managersList.add(FileManagerService.getInstance());
		managersList.add(ResourceManager.getInstance());
		managersList.add(ScreenManager.getInstance());
	}

	public static KFrontManager getInstance() {
		if (instance == null) {
			instance = new KFrontManager();
		}
		return instance;
	}

	public void onCreationComplete() {
		for (AbstractManager manager : managersList) {
			manager.onCreationComplete();
		}
	}

	public void onRefresh() {
		for (AbstractManager manager : managersList) {
			manager.onRefresh();
		}
	}

	public void onDestroy() {
		for (AbstractManager manager : managersList) {
			manager.onDestroy();
		}
	}

	public void restartApplication() {
		restartComponents();
	}

	public void restartComponents() {
		KRuntimeModel.getInstance().cleanUp();
		KModel.getInstance().cleanUp();
		onRefresh();
	}
}
