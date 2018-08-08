package impl;

import inte.BTLFactory;
import inte.MyBtn;
import inte.MyField;
import inte.MyLabel;

public class BgFactory implements BTLFactory{

	@Override
	public MyBtn createBtn() {
		// TODO 自动生成的方法存根
		return new BgBtn();
	}

	@Override
	public MyField createField() {
		// TODO 自动生成的方法存根
		return new BgField();
	}

	@Override
	public MyLabel createLabel() {
		// TODO 自动生成的方法存根
		return new BgLabel();
	}

}
