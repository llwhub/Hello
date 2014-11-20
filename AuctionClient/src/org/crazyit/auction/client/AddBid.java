package org.crazyit.auction.client;

import org.crazyit.app.base.FragmentActivity;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class AddBid extends FragmentActivity
{
	@Override
	public Fragment getFragment()
	{
		AddBidFragment fragment = new AddBidFragment();
		Bundle args = new Bundle();
		args.putInt("itemId", getIntent()
			.getIntExtra("itemId", -1));
		fragment.setArguments(args);
		return fragment;
	}
}