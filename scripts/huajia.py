"""The main CLI entry point."""

from dataclasses import dataclass
from pathlib import Path
import json


@dataclass
class Pose:
    x: float
    y: float
    angle: float

    def export(self):
        return {
            "x": {"exp": f"{self.x} m", "val": self.x},
            "y": {"exp": f"{self.y} m", "val": self.y},
            "heading": {"exp": f"{self.angle} rad", "val": self.angle},
            "intervals": 40,
            "split": False,
            "fixTranslation": True,
            "fixHeading": True,
            "overrideIntervals": False,
        }


c1 = Pose(x=3.518733263015747, y=5.551057815551758, angle=-1.0303767131053394)
c2 = Pose(x=5.435359954833984, y=5.50984001159668, angle=-2.111216507579087)
c3 = Pose(x=6.2184977531433105, y=4.14965295791626, angle=-3.1183410182726012)
c4 = Pose(x=5.414751052856445, y=2.5833775997161865, angle=-2.0303115817332302)


def template(waypoints, name="New Path"):
    return {
        "name": "New Path",
        "version": 1,
        "snapshot": {"waypoints": [], "constraints": [], "targetDt": 0.05},
        "params": {
            "waypoints": waypoints,
            "constraints": [
                {
                    "from": "first",
                    "to": None,
                    "data": {"type": "StopPoint", "props": {}},
                    "enabled": True,
                },
                {
                    "from": "last",
                    "to": None,
                    "data": {"type": "StopPoint", "props": {}},
                    "enabled": True,
                },
            ],
            "targetDt": {"exp": "0.05 s", "val": 0.05},
        },
        "trajectory": {
            "sampleType": None,
            "waypoints": [],
            "samples": [],
            "splits": [],
        },
        "events": [],
    }


def main() -> None:
    """Run the app."""
    poses = [c1, c2, c3, c4]
    for i in range(1 << len(poses)):
        Path(f"path_{i}.traj").write_text(
            json.dumps(
                template(
                    [pose.export() for j, pose in enumerate(poses) if i & (1 << j)],
                    f"Path {i}",
                ),
            )
        )
        print(i)


if __name__ == "__main__":
    main()
