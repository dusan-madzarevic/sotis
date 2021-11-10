import {Answer} from "./Answer";

export class Question{

  questionID: number;
  questionText: string;
  section: string;
  questionScore: number;
  answers: Answer[];

  constructor(data: any) {
    if (data) {
      this.questionID = data.questionID;
      this.questionText = data.questionText;
      this.section = data.section;
      this.questionScore = data.questionScore;
      this.answers = [];
      data.answers.forEach(a => {
        this.answers.push(new Answer(a));
      });
    }

  }


}
