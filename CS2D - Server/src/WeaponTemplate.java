
public class WeaponTemplate {
	private int firerate, maxAmmo, reloadTime, damage, bulletSize, bulletSpeed, cost;
	private String name;
	/**
	 * @param firerate
	 * @param maxAmmo
	 * @param reloadTime
	 * @param damage
	 * @param name
	 */
	public WeaponTemplate(int firerate, int maxAmmo, int reloadTime, int damage, int bulletSize, int bulletSpeed, String name, int cost) {
		super();
		this.firerate = firerate;
		this.maxAmmo = maxAmmo;
		this.reloadTime = reloadTime;
		this.damage = damage;
		this.bulletSize = bulletSize;
		this.bulletSpeed = bulletSpeed;
		this.name = name;
		this.cost = cost;
	}
	
	public Weapon createWeapon() {
		return new Weapon(firerate, maxAmmo, reloadTime, damage, bulletSize, bulletSpeed, name);
	}

	public int getCost() {
		return cost;
	}
	
	
	
}
