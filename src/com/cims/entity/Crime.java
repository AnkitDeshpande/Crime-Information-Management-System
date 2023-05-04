package com.cims.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Crime {
	private int crime_id;
	private String category;
	private String desc;
	private String ps_area;
	private LocalDate date;
	private String victim;

	public Crime(int crime_id, String category, String desc, String ps_area, LocalDate date, String victim) {
		super();
		this.crime_id = crime_id;
		this.category = category;
		this.desc = desc;
		this.ps_area = ps_area;
		this.date = date;
		this.victim = victim;
	}

	public Crime() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCrime_id() {
		return crime_id;
	}

	public void setCrime_id(int crime_id) {
		this.crime_id = crime_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPs_area() {
		return ps_area;
	}

	public void setPs_area(String ps_area) {
		this.ps_area = ps_area;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getVictim() {
		return victim;
	}

	public void setVictim(String victim) {
		this.victim = victim;
	}

	@Override
	public String toString() {
		return "Crime \n crime_id=" + crime_id + "\n category=" + category + "\n desc=" + desc + "\n ps_area=" + ps_area
				+ "\n date=" + date + "\n victim=" + victim + "]";
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(crime_id, category, desc, ps_area, date, victim);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Crime other = (Crime) obj;
		return Objects.equals(category, other.category) && crime_id == other.crime_id
				&& Objects.equals(victim, other.victim) && desc == other.desc;
	}
}
