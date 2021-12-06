class Course:
    def __init__(self):
        self.name = ""
        self.id = ""
        self.national_code = ""
        self.goal = ""
        self.desired_outcome = ""
        self.espb = 0
        self.subjects = []


class Programme:
    def __init__(self):
        self.name = ""
        self.id = ""
        self.duration = ""
        self.espb = 0
        self.courses = []