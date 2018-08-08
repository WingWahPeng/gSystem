package impl;

import inte.BTLFactory;
import inte.MyBtn;
import inte.MyField;
import inte.MyLabel;

public class BgFactory implements BTLFactory{

	@Override
	public MyBtn createBtn() {
		// TODO �Զ����ɵķ������
		return new BgBtn();
	}

	@Override
	public MyField createField() {
		// TODO �Զ����ɵķ������
		return new BgField();
	}

	@Override
	public MyLabel createLabel() {
		// TODO �Զ����ɵķ������
		return new BgLabel();
	}

}
