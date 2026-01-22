# YUE-AI 智能体

**YUE-AI-Agent** 是一个基于 **Java (Spring AI)** 与 **Vue** 开发的全栈大模型智能体平台。它集成了先进的 **ReAct 思考链路**、**多模态工具调用**以及**垂直领域 RAG（检索增强生成）**技术，旨在打造从情感陪伴到逻辑推理全方位 AI 体验。

## 核心应用场景

项目目前提供两大预设智能体，分别代表了 AI 的“理性”与“感性”：

- **AI 恋爱大师 (Emotion)**：深耕亲密关系领域。利用专有的心理学知识库（RAG）和记忆管理，为用户提供情感咨询、关系分析及实务陪伴。
<img width="2549" height="1403" alt="image" src="https://github.com/user-attachments/assets/030d7b83-4d3c-4cf2-962e-75909376dfb5" />

- **AI 超级智能体 (Logic)**：专注于逻辑思维的延伸。支持网页搜索、网页爬取、整合信息及生成 PDF/Markdown 文档。它能根据用户需求自主分析并调用工具完成任务。
<img width="2549" height="1403" alt="image" src="https://github.com/user-attachments/assets/3756133e-bcfe-457a-8206-9e24b8def4c4" />

## 技术核心实现

### 1. 智能决策大脑 (Agent Engine)

后端实现了多种代理模式，支撑起复杂的任务处理：

- **ReActAgent**: 严格遵循“推理-行动-观察”循环，确保任务执行的逻辑性。
- **ToolCallAgent**: 优化函数调用（Function Calling）流程，实现毫秒级的工具触发。
- **Advisor 系统**: 包含 `ReReadingAdvisor`（通过重读提升理解）与 `MyLoggerAdvisor`（全链路行为日志）。

### 2. 自动化工具箱 (Autonomous Tools)

Agent 具备直接操作环境的能力，内置多种生产力工具：

- **网络操作**: `WebSearchTool` (实时搜索)、`WebScrapingTool` (网页内容提取)。
- **文件处理**: `PDFGenerationTool` (自动生成报告)、`FileOperationTool` (本地文件读写)。
- **系统交互**: `TerminalOperationTool` (执行终端指令)。

### 3. 高级 RAG 流水线 (Retrieval Augmented Generation)

针对垂直领域（如恋爱咨询）提供精准的知识检索：

- **查询重写 (`QueryRewriter`)**: 自动优化用户模糊的提问。
- **多维向量存储**: 支持 `PgVector` 实现大规模语义数据的快速检索。
- **关键词增强**: 通过 `MyKeywordEnricher` 提升检索相关度。

### 4. 记忆系统 (Memory Management)

- **FileBasedChatMemory**: 实现了对话上下文的持久化存储，确保跨会话的记忆连续性。

## 项目结构概览

```
yue-ai-agent/
├── src/main/java/.../yueaiagent/
│   ├── agent/       # 核心代理逻辑 (ReAct/ToolCall)
│   ├── rag/         # 检索增强实现 (QueryRewrite/VectorStore)
│   ├── tools/       # 自动化工具集 (Search/Scraping/File)
│   ├── advisor/     # 提示词增强与拦截器
│   └── chatmemory/  # 记忆持久化模块
├── yue-ai-agent-frontend/ # Vue.js 响应式前端交互界面
└── Dockerfile       # 容器化一键部署支持
```
