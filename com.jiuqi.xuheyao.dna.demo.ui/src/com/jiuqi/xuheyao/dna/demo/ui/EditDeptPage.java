package com.jiuqi.xuheyao.dna.demo.ui;

import com.jiuqi.dna.core.situation.MessageListener;
import com.jiuqi.dna.core.situation.MessageTransmitter;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.DatePicker;
import com.jiuqi.dna.ui.template.launch.TemplateWindow.ButtonMessage;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.Page;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.xuheyao.dna.demo.service.task.CreateDeptSimpleTask;

public class EditDeptPage extends Page{

	private Text txtName;
	private DatePicker dp;
	
	public EditDeptPage(Composite parent, final DeptInfoEdit deptInfoEdit) {
		super(parent);
		this.setLayout(new GridLayout(2));
		
		new Label(this).setText("�������ƣ�");
		txtName = new Text(this);
		txtName.setText(deptInfoEdit.name);
		new Label(this).setText("�������ڣ�");
		dp = new DatePicker(this);
		new Label(this).setText("�ϼ����ţ�");
		
		//���Ӱ�ť������
		getContext().regMessageListener(ButtonMessage.class, new MessageListener<ButtonMessage>() {

			@Override
			public void onMessage(Situation context, ButtonMessage message,
					MessageTransmitter<ButtonMessage> transmitter) {
				//���� �༭����ʱ������ҳ���������ݸ���ҳ��
				if(message.buttonType == JWT.OK){
					DeptInfo deptInfo = new DeptInfo(txtName.getText(), dp.getDate());
					deptInfoEdit.mainPage.AddTreeItem(deptInfo);
					
					
					//����������
					CreateDeptSimpleTask simpleTask = new CreateDeptSimpleTask();
					simpleTask.name = txtName.getText();
					simpleTask.createDate = dp.getDate();
					getContext().handle(simpleTask);
				}
			}

		});
	}
	
}