package impl;

import inte.BTLFactory;
import inte.MyBtn;
import inte.MyField;
import inte.MyLabel;

public class GwFactory implements BTLFactory {

	@Override
	public MyBtn createBtn() {
		// TODO �Զ����ɵķ������
		return new GwBtn();
	}

	@Override
	public MyField createField() {
		// TODO �Զ����ɵķ������
		return new GwField();
	}

	@Override
	public MyLabel createLabel() {
		// TODO �Զ����ɵķ������
		return new GwLabel();
	}

}
