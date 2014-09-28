package ca.concordia.soen6441.logic;

public class MapValidatorImpl implements MapValidator {

	@Override
	public boolean isValid(Map map) {
		
		
		if (mapHasNoEnd(map))
		{
			return false;
		}
		if (mapHasNoStart(map))
		{
			return false;
		}
		if (mapStartPositionCount(map) > 1)
		{
			return false;
		}
		
		return true;
	}

	private int mapStartPositionCount(Map map) {
		if (map.getStartTile() == null)
		{
			return 0;
		}
//		else if (map.getStartTile() != null)
//		{
			return 1;
//		}
	}

	private boolean mapHasNoStart(Map map) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean mapHasNoEnd(Map map) {
		return map.getEndTile() == null;
	}

}
