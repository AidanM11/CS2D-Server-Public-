
public class WeaponTemplate {
	private int firerate, maxAmmo, reloadTime, damage, bulletSize;
	private String name;
	/**
	 * @param firerate
	 * @param maxAmmo
	 * @param reloadTime
	 * @param damage
	 * @param name
	 */
	public WeaponTemplate(int firerate, int maxAmmo, int reloadTime, int damage, int bulletSize, String name) {
		super();
		this.firerate = firerate;
		this.maxAmmo = maxAmmo;
		this.reloadTime = reloadTime;
		this.damage = damage;
		this.bulletSize = bulletSize;
		this.name = name;
	}
	
	public Weapon createWeapon() {
		return new Weapon(firerate, maxAmmo, reloadTime, damage, bulletSize, name);
	}
	
	
}
