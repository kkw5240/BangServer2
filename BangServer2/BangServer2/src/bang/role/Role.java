/**
 * 
 */
package bang.role;

/**
 * @author		: PROP
 * @date		: 2014. 5. 25.
 * @explanation	: 
 */
public class Role {
	public enum RoleName {
		SHERIFF(1), 
		VICE(2),
		OUTLAW(3),
		RENEGADE(4);

		private int rolNum;

		// 열거 값에 (String) 값 span 에 대입
		RoleName(int rolNum) {
			this.rolNum = rolNum;
		}

		// span 값 반환
		public int getRolNum() {
			return rolNum;
		}
	}
}
