package com.wsc.photomaker.controller;

import java.io.IOException;

import com.wsc.photomaker.app.KApplication;

public class FileManagerService extends AbstractManager{

	public static FileManagerService instance; 

	public static FileManagerService getInstance() {
		if (instance == null){
			instance = new FileManagerService();
		}
		return instance;
	}

	public String getExternalPath(){
		String filesDir = KApplication.getContext().getFilesDir().getAbsolutePath();
	//	String externalFilesDir = KApplication.getContext().getExternalFilesDir(null).getAbsolutePath();
		return filesDir;
	}
//
//	private String getAbsolutePathToApplication(){
////		String pathDefault = ResourceManager.getStringValue(R.string.path_root).replace("%env", HPModel.getInstance().getEnv());
//		String pathDefault = ResourceManager.getStringValue(R.string.path_root);//.replace("%env", "env");
//
//		return getExternalPath() + pathDefault;
//	}
//
//	public String getAbsolutePathToEvn(){
////		String pathDefault = getAbsolutePathToApplication() + ResourceManager.getStringValue(R.string.path_env).replace("%env", HPModel.getInstance().getEnv());
//		String pathDefault = getAbsolutePathToApplication() + ResourceManager.getStringValue(R.string.path_env);//.replace("%env", "env");
//		return pathDefault;
//	}

//	public String getAbsolutePathToEvnLocalized(){
//		String pathDefault = getAbsolutePathToEvn(); //+ "/" + HPModel.getInstance().getLocale().getLanguage(); 
//		return pathDefault; 
//	}

//	public String getAbsolutePathToDB(){
//		String path = ResourceManager.getStringValue(R.string.path_db);
//		return getAbsolutePathToEvnLocalized() + path; 
//	}
//
//	public String getAbsolutePathToImages(){
//		String path = ResourceManager.getStringValue(R.string.path_images);
//		return getAbsolutePathToEvn() + path; 
//	} 
//
//	public String getAbsolutePathToEtc(){
//		String path = ResourceManager.getStringValue(R.string.path_etc);
//		return getAbsolutePathToEvn() + path; 
//	}
//	public String getAbsolutePathToConfigs(){
//		String path = ResourceManager.getStringValue(R.string.path_conf);
//		return getAbsolutePathToEvn() + path; 
//	}
 


	public void updateApplicationDirStructure() throws IOException{   

//		FileUtils.rmDirStructure(getAbsolutePathToApplication());
		
//		FileUtils.mkDirStructure(getAbsolutePathToConfigs());
//		FileUtils.mkDirStructure(getAbsolutePathToDB()); 
//		FileUtils.mkDirStructure(getAbsolutePathToEtc());
//		FileUtils.mkDirStructure(getAbsolutePathToImages());
	} 

	public String getDatabaseLocPath(){
		String result = "";
		result = getExternalPath();
		
		return result;
	}  

	@Override
	public void onCreationComplete() {
		try {
			updateApplicationDirStructure();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onRefresh() {
		try {
			updateApplicationDirStructure();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDestroy() {


	}
}
