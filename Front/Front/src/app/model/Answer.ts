export class Answer{

  answerID: number;
  answerText: string;
  selected: boolean;
  constructor(data: any) {
    if (data) {
      this.answerID = data.answerID;
      this.answerText = data.answerText;
    }
}}
