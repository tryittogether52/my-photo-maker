package com.wsc.photomaker.ui.fragments.base;

import android.app.Fragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wsc.photomaker.R;
import com.wsc.photomaker.app.KApplication;
import com.wsc.photomaker.controller.ResourceManager;
import com.wsc.photomaker.utils.DialogUtils;

public class BaseFragment extends Fragment {
	protected View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// view = inflater.inflate(R.layout.fragment, container, false);
		init();
		return view;
	}

	public void init() {
		initComponents();
		initValues();
		initListeners();
	}

	public void initComponents() {
	}

	public void initValues() {
	}

	public void initListeners() {
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		// if (v.getId() == R.id.list) {
		// //AdapterView.AdapterContextMenuInfo info =
		// (AdapterView.AdapterContextMenuInfo) menuInfo;
		// menu.setHeaderTitle(ResourceManager.getStringValue(R.string.menu_choose_action));
		// MenuInflater inflater = getActivity().getMenuInflater();
		// inflater.inflate(R.menu.menu_context, menu);
		// }
	}

	public void showExceptionAsDialog(final Exception e) {
		KApplication.handler.post(new Runnable() {
			public void run() {
				DialogUtils.createOkDialog(getActivity(), ResourceManager.getStringValue(R.string.dialog_title_error), e.getMessage(), null);
			}
		});
	}
}
