export interface ProjectDTO {
    id: number | null;
    title: string;
    description: string;
    technologies: string;
    screenshots: number[];
    sourceCodeLink: string | null;
    authorId: number;
  }
  