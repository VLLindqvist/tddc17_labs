<<<<<<< HEAD
public class StateAndReward {
	private static final int ANGLE_STATES = 10;
	private static final int VX_STATES = 2;
	private static final int VY_STATES = 7;
	private static final double MIN_ANGLE = -(Math.PI - (Math.PI / 4));
	private static final double MAX_ANGLE = (Math.PI - (Math.PI / 4));
	private static final double MIN_VX = -0.5;
	private static final double MAX_VX = 0.5;
	private static final double MIN_VY = -3;
	private static final double MAX_VY = 3;
	
	/* State discretization function for the angle controller */
	public static String getStateAngle(double angle, double vx, double vy) {
		/* TODO: IMPLEMENT THIS FUNCTION */
		return "angle:" + String.valueOf((double) discretize(angle, ANGLE_STATES, MIN_ANGLE, MAX_ANGLE) / ANGLE_STATES);
	}

	/* Reward function for the angle controller */
	public static double getRewardAngle(double angle, double vx, double vy) {
		/* TODO: IMPLEMENT THIS FUNCTION */
		return (Math.abs(angle) < MAX_ANGLE) ?
			1 - (Math.abs(angle) / MAX_ANGLE)
			: 0;
	}

	/* State discretization function for the full hover controller */
	public static String getStateHover(double angle, double vx, double vy) {
		/* TODO: IMPLEMENT THIS FUNCTION */
		double dAngle = (double) discretize(angle, ANGLE_STATES, MIN_ANGLE, MAX_ANGLE) / ANGLE_STATES;
		double dVX = (double) discretize2(vx, VX_STATES, MIN_VX, MAX_VX) / VX_STATES;
		double dVY = (double) discretize2(vy, VY_STATES, MIN_VY, MAX_VY) / VY_STATES;

//		System.out.println(dVY);

		return "dangle:" + String.valueOf(dAngle) + ", dvx:" + String.valueOf(dVX) + ", dvy:" + String.valueOf(dVY);
	}

	/* Reward function for the full hover controller */
	public static double getRewardHover(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */		
		final int NORM_FACTOR = 3;

		double angle_reward = (Math.abs(angle) < MAX_ANGLE) ?
			Math.pow(1 - (Math.abs(angle) / MAX_ANGLE), 2)
			: 0;

		double vx_reward = (Math.abs(vx) < MAX_VX) ?
			1 - (Math.abs(vx) / MAX_VX)
			: 0;

		double vy_reward = (Math.abs(vy) < MAX_VY) ?
			1 - (Math.abs(vy) / MAX_VY) //MAX_VY
			: 0;
// System.out.println("angle_reward:" + angle_reward + " vx_reward:" + vx_reward + " vy_reward:" + vy_reward);
		return Math.pow((angle_reward + vx_reward + vy_reward) / NORM_FACTOR, 3) ;
	}

	/** ///////////////////////////////////////////////////////////
	/* discretize() performs a uniform discretization of the
	/* value parameter.
	/* It returns an integer between 0 and nrValues-1.
	/* The min and max parameters are used to specify the interval
	/* for the discretization.
	/* If the value is lower than min, 0 is returned
	/* If the value is higher than min, nrValues-1 is returned
	/* otherwise a value between 1 and nrValues-2 is returned.
	/*
	/* Use discretize2() if you want a discretization method that does
	/* not handle values lower than min and higher than max.
	**/ ///////////////////////////////////////////////////////////
	public static int discretize(double value, int nrValues, double min, double max) {
		if (nrValues < 2) {
			return 0;
		}

		double diff = max - min;

		if (value < min) {
			return 0;
		}
		if (value > max) {
			return nrValues - 1;
		}

		double tempValue = value - min;
		double ratio = tempValue / diff;

		return (int) (ratio * (nrValues - 2)) + 1;
	}

	/** ///////////////////////////////////////////////////////////
	/* discretize2() performs a uniform discretization of the
	/* value parameter.
	/* It returns an integer between 0 and nrValues-1.
	/* The min and max parameters are used to specify the interval
	/* for the discretization.
	/* If the value is lower than min, 0 is returned
	/* If the value is higher than min, nrValues-1 is returned
	/* otherwise a value between 0 and nrValues-1 is returned.
	**/ ///////////////////////////////////////////////////////////
	public static int discretize2(double value, int nrValues, double min, double max) {
		double diff = max - min;

		if (value < min) {
			return 0;
		}
		if (value > max) {
			return nrValues - 1;
		}

		double tempValue = value - min;
		double ratio = tempValue / diff;

		return (int) (ratio * nrValues);
	}

}
=======
public class StateAndReward {
	private static final int ANGLE_STATES = 10;
	private static final int VX_STATES = 2;
	private static final int VY_STATES = 7;
	private static final double MIN_ANGLE = -(Math.PI - (Math.PI / 4));
	private static final double MAX_ANGLE = (Math.PI - (Math.PI / 4));
	private static final double MIN_VX = -0.5;
	private static final double MAX_VX = 0.5;
	private static final double MIN_VY = -3;
	private static final double MAX_VY = 3;
	
	/* State discretization function for the angle controller */
	public static String getStateAngle(double angle, double vx, double vy) {
		/* TODO: IMPLEMENT THIS FUNCTION */
		return "angle:" + String.valueOf((double) discretize(angle, ANGLE_STATES, MIN_ANGLE, MAX_ANGLE));
	}

	/* Reward function for the angle controller */
	public static double getRewardAngle(double angle, double vx, double vy) {
		/* TODO: IMPLEMENT THIS FUNCTION */
		return (Math.abs(angle) < MAX_ANGLE) ?
			Math.pow(1 - (Math.abs(angle) / MAX_ANGLE), 2)
			: 0;
	}

	/* State discretization function for the full hover controller */
	public static String getStateHover(double angle, double vx, double vy) {
		/* TODO: IMPLEMENT THIS FUNCTION */
		double dVX = (double) discretize2(vx, VX_STATES, MIN_VX, MAX_VX);
		double dVY = (double) discretize2(vy, VY_STATES, MIN_VY, MAX_VY);

		return "dangle:" + getStateAngle(angle, vx, vy)
					+ ", dvx:" + String.valueOf(dVX)
					+ ", dvy:" + String.valueOf(dVY);
	}

	/* Reward function for the full hover controller */
	public static double getRewardHover(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */		
		final int NORM_FACTOR = 3;

		double angle_reward = getRewardAngle(angle, vx, vy);

		double vx_reward = (Math.abs(vx) < MAX_VX) ?
			1 - (Math.abs(vx) / MAX_VX)
			: 0;

		double vy_reward = (Math.abs(vy) < MAX_VY) ?
			1 - (Math.abs(vy) / MAX_VY)
			: 0;

		return Math.pow((angle_reward + vx_reward + vy_reward) / NORM_FACTOR, 3);
	}

	/** ///////////////////////////////////////////////////////////
	/* discretize() performs a uniform discretization of the
	/* value parameter.
	/* It returns an integer between 0 and nrValues-1.
	/* The min and max parameters are used to specify the interval
	/* for the discretization.
	/* If the value is lower than min, 0 is returned
	/* If the value is higher than min, nrValues-1 is returned
	/* otherwise a value between 1 and nrValues-2 is returned.
	/*
	/* Use discretize2() if you want a discretization method that does
	/* not handle values lower than min and higher than max.
	**/ ///////////////////////////////////////////////////////////
	public static int discretize(double value, int nrValues, double min, double max) {
		if (nrValues < 2) {
			return 0;
		}

		double diff = max - min;

		if (value < min) {
			return 0;
		}
		if (value > max) {
			return nrValues - 1;
		}

		double tempValue = value - min;
		double ratio = tempValue / diff;

		return (int) (ratio * (nrValues - 2)) + 1;
	}

	/** ///////////////////////////////////////////////////////////
	/* discretize2() performs a uniform discretization of the
	/* value parameter.
	/* It returns an integer between 0 and nrValues-1.
	/* The min and max parameters are used to specify the interval
	/* for the discretization.
	/* If the value is lower than min, 0 is returned
	/* If the value is higher than min, nrValues-1 is returned
	/* otherwise a value between 0 and nrValues-1 is returned.
	**/ ///////////////////////////////////////////////////////////
	public static int discretize2(double value, int nrValues, double min, double max) {
		double diff = max - min;

		if (value < min) {
			return 0;
		}
		if (value > max) {
			return nrValues - 1;
		}

		double tempValue = value - min;
		double ratio = tempValue / diff;

		return (int) (ratio * nrValues);
	}

}
>>>>>>> 729d599ed6493ac5b59074bba5da5e15c5514a1d
