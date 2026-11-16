# FTC Robot Architecture

**Architecture Version:** 1.0  
**Season:** FTC BioBuzz 2026–2027  
**Language:** Java  
**Framework:** FTC SDK

## 1. Architectural Philosophy

The project follows a **Modular Subsystem Architecture**.

The robot is divided into independent subsystems. Each subsystem is responsible for controlling a specific part of the robot.

The architecture prioritizes:

- Separation of Concerns.
- Modularity and maintainability.
- Reusability across TeleOp and Autonomous.
- Non-blocking execution.
- Clear ownership of hardware.
- Easy integration of new mechanisms.

The architecture should remain simple and avoid unnecessary abstractions.

## 2. Project Structure

```text
org.firstinspires.ftc.teamcode/
│
├── constants/
│   ├── DevicesName.java
│   └── RobotConstants.java
│
├── subsystems/
│   ├── drivetrain/
│   │   ├── DriveTrain.java
│   │   └── DriveConstants.java
│   │
│   └── vision/
│       └── (Future implementation)
│
├── robot/
│   └── Robot.java
│
└── opmodes/
    ├── teleop/
    └── autonomous/
```

New subsystems will be introduced when their mechanical design and responsibilities are known.

## 3. Architectural Layers

### 3.1 Constants

Contains shared configuration values.

Examples:

- Hardware device names.
- Robot configuration values.
- Subsystem-specific constants.

Constants should be grouped according to their responsibilities.

### 3.2 Subsystems

Each subsystem owns and controls its related hardware.

Responsibilities:

- Hardware initialization.
- Mechanism control.
- Reading relevant sensors.
- Maintaining internal state when necessary.
- Providing a clear public interface.

Subsystems should not depend on OpMode-specific gamepad logic.

### 3.3 Robot

The Robot class acts as the central composition point.

Responsibilities:

- Initialize subsystems.
- Hold subsystem instances.
- Provide a central `update()` method.
- Coordinate subsystem-level updates where appropriate.

### 3.4 OpModes

OpModes define the robot's behavior.

**TeleOp:**

- Read gamepad input.
- Translate operator input into subsystem commands.
- Coordinate robot behavior.

**Autonomous:**

- Execute autonomous state machines.
- Use sensor feedback.
- Coordinate subsystem actions.

OpModes should not directly implement low-level motor control.

## 4. Execution Model

The project uses the FTC SDK iterative `OpMode` model:

- `init()` initializes the robot.
- `loop()` runs repeatedly.
- Subsystems expose `update()` methods when periodic processing is required.

The main execution flow:

```text
OpMode.loop()
    |
    v
Robot.update()
    |
    ├── DriveTrain.update()
    ├── Vision.update()
    └── OtherSubsystems.update()
```

Periodic updates must be non-blocking.

Avoid using long-running loops or blocking delays inside subsystem updates.

## 5. State Machines

State Machines will be used to represent multi-step behaviors.

A state machine should:

- Have explicit states.
- Define clear transition conditions.
- Use sensor feedback when appropriate.
- Avoid blocking execution.
- Allow the rest of the robot to continue updating.

Autonomous behavior will be built using these principles.

## 6. Hardware Ownership

Each hardware device should have a clear owner.

For example:

- DriveTrain owns drivetrain motors and related sensors.
- Vision owns camera-related resources.
- Other subsystems own their respective hardware.

Hardware should not be independently initialized or controlled by multiple unrelated classes.

## 7. Design Principles

1. Prefer simple designs over unnecessary abstractions.
2. Keep subsystem responsibilities focused.
3. Keep hardware initialization in the owning subsystem.
4. Separate robot behavior from low-level hardware control.
5. Avoid blocking operations in periodic execution.
6. Add abstractions only when they solve an actual problem.
7. Keep the architecture adaptable to future robot mechanisms.
