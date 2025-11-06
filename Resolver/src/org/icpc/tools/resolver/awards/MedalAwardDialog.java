package org.icpc.tools.resolver.awards;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;
import org.icpc.tools.contest.model.IAward;
import org.icpc.tools.contest.model.IAward.AwardType;
import org.icpc.tools.contest.model.internal.Contest;
import org.icpc.tools.contest.model.util.AwardUtil;

public class MedalAwardDialog extends AbstractAwardDialog {
	private int numGold = 4;
	private int numSilver = 4;
	private int numBronze = 4;

	public MedalAwardDialog(Shell parent, Contest contest) {
		super(parent, contest);
	}

	@Override
	protected String getTitle() {
		return "Medal Awards";
	}

	@Override
	protected String getDescription() {
		return "Assign gold, silver, and bronze medals.";
	}

	@Override
	protected void createAwardUI(Composite comp) {
		Label label = new Label(comp, SWT.NONE);
		label.setText("Number of gold:");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));

		final Text goldText = new Text(comp, SWT.BORDER | SWT.SINGLE);
		goldText.setText("4"); // 设置默认值
		GridData goldData = new GridData(SWT.BEGINNING, SWT.CENTER, true, false);
		goldData.horizontalSpan = 2;
		goldData.widthHint = 30;
		goldText.setLayoutData(goldData);

		goldText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				try {
					numGold = Integer.parseInt(goldText.getText());
					updateAwards();
				} catch (NumberFormatException ex) {
					// 处理非数字输入，可以设置默认值或显示错误信息
					// numGold = 0; // 可选：设置默认值
					// updateAwards();
				}
			}
		});

		goldText.addVerifyListener(new VerifyListener() {
			@Override
			public void verifyText(VerifyEvent e) {
				String newText = e.text;
				for (int i = 0; i < newText.length(); i++) {
					if (!Character.isDigit(newText.charAt(i))) {
						e.doit = false;
						return;
					}
				}
			}
		});

		label = new Label(comp, SWT.NONE);
		label.setText("Number of silver:");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));

		final Text silverText = new Text(comp, SWT.BORDER | SWT.SINGLE);
		silverText.setText("4"); // 设置默认值
		GridData silverData = new GridData(SWT.BEGINNING, SWT.CENTER, true, false);
		silverData.horizontalSpan = 2;
		silverData.widthHint = 30;
		silverText.setLayoutData(silverData);

		silverText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				try {
					numSilver = Integer.parseInt(silverText.getText());
					updateAwards();
				} catch (NumberFormatException ex) {
					// 处理非数字输入，可以设置默认值或显示错误信息
					// numSilver = 0; // 可选：设置默认值
					// updateAwards();
				}
			}
		});

		silverText.addVerifyListener(new VerifyListener() {
			@Override
			public void verifyText(VerifyEvent e) {
				String newText = e.text;
				for (int i = 0; i < newText.length(); i++) {
					if (!Character.isDigit(newText.charAt(i))) {
						e.doit = false;
						return;
					}
				}
			}
		});

		label = new Label(comp, SWT.NONE);
		label.setText("Number of bronze:");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));

		final Text bronzeText = new Text(comp, SWT.BORDER | SWT.SINGLE);
		bronzeText.setText("4"); // 设置默认值
		GridData bronzeData = new GridData(SWT.BEGINNING, SWT.CENTER, true, false);
		bronzeData.horizontalSpan = 2;
		bronzeData.widthHint = 30;
		bronzeText.setLayoutData(bronzeData);

		bronzeText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				try {
					numBronze = Integer.parseInt(bronzeText.getText());
					updateAwards();
				} catch (NumberFormatException ex) {
					// 处理非数字输入，可以设置默认值或显示错误信息
					// numBronze = 0; // 可选：设置默认值
					// updateAwards();
				}
			}
		});

		bronzeText.addVerifyListener(new VerifyListener() {
			@Override
			public void verifyText(VerifyEvent e) {
				// 只允许数字输入
				String newText = e.text;
				for (int i = 0; i < newText.length(); i++) {
					if (!Character.isDigit(newText.charAt(i))) {
						e.doit = false;
						return;
					}
				}
			}
		});
	}

	@Override
	protected AwardType[] getAwardTypes() {
		return new AwardType[] { IAward.MEDAL };
	}

	@Override
	protected void applyAwards(Contest aContest) {
		AwardUtil.createMedalAwards(aContest, numGold, numSilver, numBronze);
	}
}