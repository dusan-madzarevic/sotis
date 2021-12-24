import { Problem } from "./Problem"

export class Surmise{

    id: number;
    knowledgeSpaceId: number;
    problemFrom: Problem;
    problems: Problem[];
    constructor(data: any) {
      if (data) {
        this.id = data.id;
        this.knowledgeSpaceId = data.knowledgeSpaceId;
        this.problemFrom = data.problemFrom;
        this.problems = data.problems;
      }
  }}
  