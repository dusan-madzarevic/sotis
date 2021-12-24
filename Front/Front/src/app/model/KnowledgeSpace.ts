import { Surmise } from "./Surmise";

export class KnowledgeSpace{

    id: number;
    name: string;
    subjectId: number;
    realSpace: boolean;
    surmises: Surmise[];
    constructor(data: any) {
      if (data) {
        this.id = data.id;
        this.name = data.name;
        this.subjectId = data.subjectId;
        this.realSpace = data.realSpace;
        this.surmises = data.surmises;
      }
  }}
  