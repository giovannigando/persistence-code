package persistence.keyvalues;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RobotPart {
	
	@XmlElement(required=true)
	public String name;
	public String description;
	public String supplier;
	public int weight;
	@Override
	public String toString() {
		return "RobotPart [name=" + name + ", description=" + description + ", supplier=" + supplier + ", weight="
				+ weight + "]";
	}
	
}


