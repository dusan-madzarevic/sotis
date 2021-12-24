export class Problem{

    id: number;
    subjectId: number;
    name: string;
    description: string;
    constructor(data: any) {
      if (data) {
        this.id = data.id;
        this.subjectId = data.subjectId;
        this.name = data.name;
        this.description = data.description;
      }
  }}
  