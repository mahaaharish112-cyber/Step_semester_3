import java.util.ArrayList;
import java.util.List;

// Capability Interface
interface Capability {
    String getName();
    void apply(String value);
}

// Power Capability
class PowerCapability implements Capability {
    private boolean poweredOn;

    @Override
    public String getName() {
        return "Power";
    }

    @Override
    public void apply(String value) {
        if (value.equalsIgnoreCase("ON")) {
            poweredOn = true;
            System.out.println("Power turned ON");
        } 
        else if (value.equalsIgnoreCase("OFF")) {
            poweredOn = false;
            System.out.println("Power turned OFF");
        } 
        else {
            System.out.println("Invalid power value");
        }
    }
}

// Brightness Capability
class BrightnessCapability implements Capability {
    private int brightness;

    @Override
    public String getName() {
        return "Brightness";
    }

    @Override
    public void apply(String value) {
        int level = Integer.parseInt(value);

        if (level >= 0 && level <= 100) {
            brightness = level;
            System.out.println("Brightness set to " + brightness + "%");
        } 
        else {
            System.out.println(
                "Invalid brightness: " + level +
                "%. Range must be 0-100."
            );
        }
    }
}

// Temperature Capability
class TemperatureCapability implements Capability {
    private int temperature;

    @Override
    public String getName() {
        return "Temperature";
    }

    @Override
    public void apply(String value) {
        int temp = Integer.parseInt(value);

        if (temp >= 16 && temp <= 30) {
            temperature = temp;
            System.out.println(
                "Temperature set to " + temperature + "°C"
            );
        } 
        else {
            System.out.println(
                "Invalid temperature: " + temp +
                "°C. Range must be 16-30°C."
            );
        }
    }
}

// Device Class
class Device {
    private String name;
    private List<Capability> capabilities;

    public Device(String name) {
        this.name = name;
        capabilities = new ArrayList<>();
    }

    public void addCapability(Capability capability) {
        capabilities.add(capability);

        System.out.println(
            capability.getName() +
            " capability added to " + name
        );
    }

    public void applyCapability(String capabilityName, String value) {

        for (Capability capability : capabilities) {

            if (capability.getName().equalsIgnoreCase(capabilityName)) {
                capability.apply(value);
                return;
            }
        }

        System.out.println(
            name + " does not support " + capabilityName
        );
    }

    public boolean hasCapability(String capabilityName) {

        for (Capability capability : capabilities) {

            if (capability.getName().equalsIgnoreCase(capabilityName)) {
                return true;
            }
        }

        return false;
    }

    public String getName() {
        return name;
    }
}

// Scene Step
class SceneStep {
    private String capabilityName;
    private String value;

    public SceneStep(String capabilityName, String value) {
        this.capabilityName = capabilityName;
        this.value = value;
    }

    public void execute(Device device) {

        if (device.hasCapability(capabilityName)) {
            System.out.println(
                device.getName() + " -> " +
                capabilityName + " = " + value
            );

            device.applyCapability(capabilityName, value);
        }
    }
}

// Scene Class
class Scene {
    private String name;
    private List<SceneStep> steps;

    public Scene(String name) {
        this.name = name;
        steps = new ArrayList<>();
    }

    public void addStep(SceneStep step) {
        steps.add(step);
    }

    public void execute(List<Device> devices) {

        System.out.println("\nExecuting Scene: " + name);

        for (SceneStep step : steps) {

            for (Device device : devices) {
                step.execute(device);
            }
        }
    }
}

// Main Class
public class SmartLabControlPanel {

    public static void main(String[] args) {

        // Create devices
        Device ac = new Device("Lab AC");
        Device lights = new Device("Ceiling Lights");
        Device projector = new Device("Projector");

        // Add capabilities
        ac.addCapability(new PowerCapability());
        ac.addCapability(new TemperatureCapability());

        lights.addCapability(new PowerCapability());
        lights.addCapability(new BrightnessCapability());

        projector.addCapability(new PowerCapability());

        // Add devices to the lab
        List<Device> devices = new ArrayList<>();
        devices.add(ac);
        devices.add(lights);
        devices.add(projector);

        // Create Lecture Mode scene
        Scene lectureMode = new Scene("Lecture Mode");

        lectureMode.addStep(
            new SceneStep("Power", "ON")
        );

        lectureMode.addStep(
            new SceneStep("Brightness", "40")
        );

        lectureMode.addStep(
            new SceneStep("Temperature", "24")
        );

        // Execute scene
        lectureMode.execute(devices);

        // Test invalid temperature
        System.out.println("\nTesting invalid temperature:");

        ac.applyCapability("Temperature", "12");

        // Runtime capability addition
        System.out.println("\nAdding Brightness to Projector:");

        projector.addCapability(
            new BrightnessCapability()
        );

        // Set projector brightness
        projector.applyCapability(
            "Brightness",
            "70"
        );
    }
}
