import {Question} from "./Question";

export class Test{

    attemptID: number;
    testName: string;
    questions: Question[];

  constructor(data: any) {
    if (data) {
      this.attemptID = data.attemptID;
      this.testName = data.testName;
      this.questions = [];
      data.questions.forEach(q => {
        this.questions.push(new Question(q));
      });
    }

  }




}
