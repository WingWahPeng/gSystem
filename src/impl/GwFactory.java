package impl;

import inte.BTLFactory;
import inte.MyBtn;
import inte.MyField;
import inte.MyLabel;

public class GwFactory implements BTLFactory {

	@Override
	public MyBtn createBtn() {
		// TODO 自动生成的方法存根
		return new GwBtn();
	}

	@Override
	public MyField createField() {
		// TODO 自动生成的方法存根
		return new GwField();
	}

	@Override
	public MyLabel createLabel() {
		// TODO 自动生成的方法存根
		return new GwLabel();
	}

}
