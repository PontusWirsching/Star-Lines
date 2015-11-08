package pontus.starlines.core.physics;

import java.util.ArrayList;

public class World {

	public ArrayList<GameObject> objects = new ArrayList<GameObject>();

	public int CHECK_RADUIS = 2000;

	public World() {

	}

	public void addObject(GameObject obj) {
		objects.add(obj);
	}

	public GameObject getObject(int index) {
		return objects.get(index);
	}

	public GameObject getObject(String ID) {
		for (GameObject obj : objects) {
			if (obj != null && obj.ID.equals(ID)) { return obj; }
		}
		return null;
	}

	/**
	 * Can object 1 collide with object 2?
	 */
	public boolean canCollide(GameObject object1, GameObject object2) {
		for (String clip : object1.clip) {
			if (clip.equals(object2.ID)) { return false; }
		}
		return true;
	}

	public void update(float delta) {

		for (int i = 0; i < objects.size(); i++) {
			if (i >= objects.size()) continue;
			GameObject obj = getObject(i);
			if (obj == null) continue;

			for (int step = 0; step < Math.round(Math.abs(obj.velocity.x)) * delta; step++) {
				boolean b = false;
				for (int j = 0; j < objects.size(); j++) {
					if (j >= objects.size()) continue;
					GameObject o = getObject(j);
					if (o == null) continue;
					if (o == obj) continue;

					if (!canCollide(obj, o)) {
						continue;
					}

					if (obj.velocity.x < 0) {
						if (obj.position.y + obj.size.y / 2 > o.position.y - o.size.y / 2 + 1 && obj.position.y - obj.size.y / 2 < o.position.y + o.size.y / 2) if (obj.position.x - obj.size.x / 2 <= o.position.x + o.size.x / 2 + 1 && obj.position.x + obj.size.x / 2 > o.position.x + o.size.x / 2) {
							obj.velocity.x = 0;
							b = true;
							break;
						}
					}

					if (obj.velocity.x > 0) {
						if (obj.position.y + obj.size.y / 2 > o.position.y - o.size.y / 2 && obj.position.y - obj.size.y / 2 < o.position.y + o.size.y / 2 - 1) if (obj.position.x - obj.size.x / 2 < o.position.x + o.size.x / 2 && obj.position.x + obj.size.x / 2 >= o.position.x - o.size.x / 2 - 1) {
							obj.velocity.x = 0;
							b = true;
							break;
						}
					}

				}
				if (b) break;

				obj.position.x += obj.velocity.x / Math.abs(obj.velocity.x);
			}

			for (int step = 0; step < Math.round(Math.abs(obj.velocity.y * delta)); step++) {
				boolean b = false;
				for (int j = 0; j < objects.size(); j++) {
					if (j >= objects.size()) continue;
					GameObject o = getObject(j);
					if (o == null) continue;
					if (o == obj) continue;

					if (!canCollide(obj, o)) {
						continue;
					}

					if (obj.velocity.y > 0) {
						if (obj.position.x + obj.size.x / 2 > o.position.x - o.size.x / 2 && obj.position.x - obj.size.x / 2 < o.position.x + o.size.x / 2) if (obj.position.y - obj.size.y / 2 < o.position.y + o.size.y / 2 && obj.position.y + obj.size.y / 2 >= o.position.y - o.size.y / 2) {
							obj.velocity.y = 0;
							b = true;
							break;
						}
					}
					if (obj.velocity.y < 0) {
						if (obj.position.x + obj.size.x / 2 > o.position.x - o.size.x / 2 && obj.position.x - obj.size.x / 2 < o.position.x + o.size.x / 2) if (obj.position.y - obj.size.y / 2 <= o.position.y + o.size.y / 2 && obj.position.y + obj.size.y / 2 > o.position.y + o.size.y / 2) {
							obj.velocity.y = 0;
							b = true;
							break;
						}
					}
				}
				if (b) break;
				obj.position.y += obj.velocity.y / Math.abs(obj.velocity.y);
			}

		}

	}

}
