package personal.learning.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Engine {

    @JsonProperty("type")
    private String type;

    @JsonProperty("cc")
    private int cc;

    @JsonProperty("torque")
    private String torque;

    public Engine() { }

	public Engine(String type, int cc, String torque) {
		this.type = type;
		this.cc = cc;
		this.torque = torque;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCc() {
		return cc;
	}

	public void setCc(int cc) {
		this.cc = cc;
	}

	public String getTorque() {
		return torque;
	}

	public void setTorque(String torque) {
		this.torque = torque;
	}

	@Override
    public String toString() {
        return "Engine [type=" + type + ", cc=" + cc + ", torque=" + torque + "]";
    }
}

