/**
 * 08672 HW4
 * @author: Kaifu Wang
 * @date:   2015-12-13 04:06:14
 * @andrewId: kaifuw
 * Last Modified by:  Kaifu Wang
 * LastModified time: 2015-12-13 04:56:41
 */
package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class UserForm extends FormBean {
	private String userId = "";

	public String getUserId()  { return userId; }

	public void setUserId(String s)  { userId = trimAndConvert(s,"<>>\"]"); }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userId == null || userId.length() == 0) {
			errors.add("User Id is required");
		}

		return errors;
	}
}
